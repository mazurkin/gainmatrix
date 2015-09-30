[#ftl encoding="UTF-8" strict_syntax="true" strip_whitespace="true"]

[#-- @ftlvariable name="bean1" type="java.util.Date" --]

[#import "/com/gainmatrix/resources/freemarker/macro/core/helpers.ftl" as helpers]

<div class='test1'>${helpers.getTimeMs(bean1)}</div>