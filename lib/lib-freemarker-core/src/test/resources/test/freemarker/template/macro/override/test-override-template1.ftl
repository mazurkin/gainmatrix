[#ftl encoding="UTF-8" strict_syntax="true" strip_whitespace="true"]

[#import "/test/freemarker/template/macro/override/test-override-macro.ftl" as macros]

[#-- Override is on --]

[#macro content_div value]<div class="local">${value}</div>[/#macro]
[#assign content=content_div in macros]

{TEST1}[@macros.html value="Hello!"/]{TEST1}

