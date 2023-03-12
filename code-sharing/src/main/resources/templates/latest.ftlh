<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <title>Latest</title>
    <meta charset="UTF-8">

    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
        <table>
            <tr>
                <th></th>
            </tr>
                <#list list as latest>
                        <tr>
                            <td>
                            <pre id="code_snippet"><code>${latest.code}</code></pre>
                            <span id="load_date">${latest.date}</span>
                            </td>
                        </tr>
                </#list>
        </table>
</body>
