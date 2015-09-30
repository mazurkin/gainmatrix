[#ftl encoding="UTF-8" strict_syntax="true" strip_whitespace="true"]

[#-- @ftlvariable name="bean1" type="java.util.List" --]

[#import "/com/gainmatrix/resources/freemarker/macro/core/helpers.ftl" as helpers]

<div class='test1'>[@helpers.outputList list=bean1/]</div>
<div class='test2'>[@helpers.outputList list=bean1 separator=","/]</div>