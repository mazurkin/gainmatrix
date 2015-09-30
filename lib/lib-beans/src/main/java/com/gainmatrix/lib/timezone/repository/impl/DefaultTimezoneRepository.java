package com.gainmatrix.lib.timezone.repository.impl;

import com.gainmatrix.lib.preconditions.Preconditions2;
import com.gainmatrix.lib.time.Chronometer;
import com.gainmatrix.lib.timezone.repository.TimezoneDefinition;
import com.gainmatrix.lib.timezone.repository.TimezoneRepository;
import com.google.common.base.Preconditions;
import org.apache.commons.lang.ArrayUtils;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Репозиторий основанный на списке временных зон в JDK
 */
public class DefaultTimezoneRepository implements TimezoneRepository {

    private Chronometer chronometer;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkState(chronometer != null, "Chronometer is not set");
    }

    @Override
    public List<TimezoneDefinition> getTimezoneDefinitions(Locale displayLocale) {
        Locale usedLocale;

        if (displayLocale != null) {
            usedLocale = displayLocale;
        } else {
            usedLocale = Locale.getDefault();
        }

        // Получаем список таймзон от системы
        String[] ids = TimeZone.getAvailableIDs();

        // Формируем результатирующий список
        List<TimezoneDefinition> definitions = new ArrayList<TimezoneDefinition>(ids.length);

        Date now = chronometer.getCurrentMoment();

        for (String id : ids) {
            TimeZone timeZone = TimeZone.getTimeZone(id);

            String timeZoneCode = timeZone.getID();
            String timeZoneName = timeZone.getDisplayName(usedLocale);
            int timeZoneOffset = timeZone.getOffset(now.getTime());
            TimezoneDefinition definition = new TimezoneDefinition(timeZoneCode, timeZoneName, timeZoneOffset);

            definitions.add(definition);
        }

        Comparator<TimezoneDefinition> definitionComparator =
            new Comparator<TimezoneDefinition>() {
                @Override
                public int compare(TimezoneDefinition o1, TimezoneDefinition o2) {
                    String name1 = o1.getCode();
                    String name2 = o2.getCode();
                    return name1.compareTo(name2);
                }
            };

        Collections.sort(definitions, definitionComparator);

        return Collections.unmodifiableList(definitions);
    }

    @Override
    public TimezoneDefinition getTimezoneDefinition(String code, Locale displayLocale) {
        Preconditions2.checkNotBlank(code, "Code is not set");

        Locale usedLocale;

        if (displayLocale != null) {
            usedLocale = displayLocale;
        } else {
            usedLocale = Locale.getDefault();
        }

        // Получаем список таймзон от системы
        String[] ids = TimeZone.getAvailableIDs();

        if (!ArrayUtils.contains(ids, code)) {
            return null;
        }

        // Формируем дескриптор
        Date now = chronometer.getCurrentMoment();

        TimeZone timeZone = TimeZone.getTimeZone(code);
        String timeZoneCode = timeZone.getID();
        String timeZoneName = timeZone.getDisplayName(usedLocale);
        int timeZoneOffset = timeZone.getOffset(now.getTime());
        return new TimezoneDefinition(timeZoneCode, timeZoneName, timeZoneOffset);
    }

    public void setChronometer(Chronometer chronometer) {
        this.chronometer = chronometer;
    }

}
