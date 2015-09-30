[#ftl encoding="UTF-8" strict_syntax="true" strip_whitespace="true"]

[#-- @ftlvariable name="bean1" type="java.util.Map" --]

[#import "/com/gainmatrix/resources/freemarker/macro/core/helpers.ftl" as helpers]

<div class='test1'>[@helpers.outputMap map=bean1/]</div>
<div class='test2'>[@helpers.outputMap map=bean1 separator=","/]</div>