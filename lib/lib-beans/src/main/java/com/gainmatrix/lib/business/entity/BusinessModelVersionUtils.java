package com.gainmatrix.lib.business.entity;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Утилитные функции для работы с версией бизнес-модели
 */
public final class BusinessModelVersionUtils {

    public static final int UNKNOWN_VERSION = Integer.MAX_VALUE;

    private BusinessModelVersionUtils() {
    }

    /**
     * Загрузка версии метамодели из файла META-INF/MANIFEST.MF и поля Implementation-Version. Этот файл и поле
     * создаются и инициализируются при построении пакета средствами Maven который в курсе какая именно версия пакета
     * сейчас создается. Пакет (JAR) с файлов версии определяется по передаваемому классу.
     * <p/>
     * Если версию по каким-то причинам определить не удается - возвращается предопределенная константа
     * BusinessModelVersionUtils#UNKNOWN_VERSION.
     * <p/>
     * Правила перевода версии пакета в версию мета-модели описаны в соседней функции.
     *
     * @param clazz Класс по которому будет определен пакет с файлом версии
     * @return Версия метамодели
     * @see BusinessModelVersionUtils#UNKNOWN_VERSION
     * @see BusinessModelVersionUtils#parseBusinessModelVersion(java.lang.String)
     */
    public static int parseBusinessModelVersion(Class clazz) {
        if (clazz == null) {
            return UNKNOWN_VERSION;
        }

        if (clazz.getPackage() == null) {
            return UNKNOWN_VERSION;
        }

        String version = clazz.getPackage().getImplementationVersion();

        if (version == null) {
            return UNKNOWN_VERSION;
        }

        return parseBusinessModelVersion(version);
    }

    /**
     * Загрузка версии метамодели из текстовой версии пакета в виде 1.4-SNAPSHOT, 2.34, 3.2.1.2
     * <p/>
     * Правила перевода версии пакета в версию метамодели следующие: берутся две крайние левые версионные секции
     * (major и minor) и вычисляется число по формуле: major * 10000 + minor * 10, все другие версионные секции
     * стоящие правее игнорируются. Далее, если в строке версии НЕ присутствует тэг "-SNAPSHOT", то к полученному числу
     * добавляется 1.
     * <p/>
     * Примеры:<br/>
     * <table>
     *     <tr><th>Версия пакета</th><th>Версия мета-модели</th></tr>
     *     <tr><td>1.2-SNAPSHOT</td><td>10020</td></tr>
     *     <tr><td>1.2</td><td>10021</td></tr>
     *     <tr><td>1.2.3.4</td><td>10021</td></tr>
     * </table>
     *
     * <p/>
     * Если версию по каким-то причинам определить не удается - возвращается предопределенная константа
     * BusinessModelVersionUtils#UNKNOWN_VERSION.
     *
     * @param version Строка с версией пакета
     * @return Версия метамодели
     * @see BusinessModelVersionUtils#UNKNOWN_VERSION
     */
    public static int parseBusinessModelVersion(String version) {
        if (version == null) {
            return UNKNOWN_VERSION;
        }

        BusinessModelVersionMatcher matcher = new BusinessModelVersionMatcher(version);

        if (!matcher.matches()) {
            return UNKNOWN_VERSION;
        }

        int versionMajor = matcher.getVersionMajor();
        int versionMinor = matcher.getVersionMinor();
        boolean release = matcher.isRelease();

        if (versionMajor >= BusinessModelVersionConstants.MAJOR_MAXIMUM) {
            throw new IllegalStateException("Major version is too high: " + versionMajor);
        }

        if (versionMinor >= BusinessModelVersionConstants.MINOR_MAXIMUM) {
            throw new IllegalStateException("Minor version is too high: " + versionMinor);
        }

        int result = 0;
        result += versionMajor * BusinessModelVersionConstants.MAJOR_MULTIPLIER;
        result += versionMinor * BusinessModelVersionConstants.MINOR_MULTIPLIER;
        result += release ?
                BusinessModelVersionConstants.RELEASE_INCREMENT :
                BusinessModelVersionConstants.SNAPSHOT_INCREMENT;
        return result;
    }

    private static final class BusinessModelVersionMatcher {

        private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)*(-SNAPSHOT)?");

        private static final int INDEX_VERSION_MAJOR = 1;

        private static final int INDEX_VERSION_MINOR = 2;

        private static final int INDEX_RELEASE = 4;

        private final Matcher matcher;

        public BusinessModelVersionMatcher(String text) {
            this.matcher = VERSION_PATTERN.matcher(text);
        }

        public boolean matches() {
            return matcher.matches();
        }

        public int getVersionMajor() {
            String strValue = matcher.group(INDEX_VERSION_MAJOR);
            return Integer.parseInt(strValue);
        }

        public int getVersionMinor() {
            String strValue = matcher.group(INDEX_VERSION_MINOR);
            return Integer.parseInt(strValue);
        }

        public boolean isRelease() {
            String strValue = matcher.group(INDEX_RELEASE);
            return StringUtils.isEmpty(strValue);
        }

    }

}
