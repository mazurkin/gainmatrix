[#ftl encoding="UTF-8" strict_syntax="true" strip_whitespace="true"]

[#-- @ftlvariable name="beanList" type="java.util.List" --]
[#-- @ftlvariable name="beanMap" type="java.util.Map" --]
[#-- @ftlvariable name="exception" type="java.lang.Exception" --]

[#import "/com/gainmatrix/resources/freemarker/macro/web/system.ftl" as system]

<div class="test1">[@system.msg code="test.message"/]</div>

<div class="test2">[@system.msgParams code="test.message" params=beanList/]</div>

<div class="test3">[@system.url relativeUrl="/some/page.html"/]</div>

<div class="test4">[@system.urlParams relativeUrl="/some/page.html?id={key1}" params=beanMap/]</div>

<div class="test5">[@system.resource relativeUrl="/some/page.html"/]</div>

<div class="test6">[@system.exceptionDump throwable=exception/]</div>