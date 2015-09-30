[#ftl encoding="UTF-8" strict_syntax="true" strip_whitespace="true"]

[#macro content value]<div class="global">${value}</div>[/#macro]

[#macro body value]<body>[@content value/]</body>[/#macro]

[#macro html value]<html>[@body value/]</html>[/#macro]