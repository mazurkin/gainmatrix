[#ftl encoding="UTF-8" strict_syntax="true" strip_whitespace="true"]

[#import "/test/freemarker/template/macro/override/test-override-macro.ftl" as macros]

[#-- No override - original macro should be back --]

{TEST1}[@macros.html value="Hello!"/]{TEST1}

