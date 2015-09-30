[#ftl encoding="UTF-8" strict_syntax="true" strip_whitespace="true"]

[#-- @ftlvariable name="bean1" type="java.lang.Boolean" --]
[#-- @ftlvariable name="bean2" type="java.lang.String" --]

[#import "/com/gainmatrix/resources/freemarker/macro/core/helpers.ftl" as helpers]

<div class='test1'>[#if helpers.isEqualText(bean1,bean2)]found[/#if]</div>