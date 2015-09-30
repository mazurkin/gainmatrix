[#ftl encoding="UTF-8" strict_syntax="true" strip_whitespace="true"]

[#-- @ftlvariable name="bean1" type="java.lang.Boolean" --]

[#import "/com/gainmatrix/resources/freemarker/macro/core/helpers.ftl" as helpers]

<div class='test1'>${helpers.getText(bean1)}</div>