<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <title>Code-and-Comments</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar" style="margin-bottom:50px;">
        <a class="navbar-brand">Snippets</a>
    </nav>
     <div style="display:flex; justify-content:center; align-items: center;">
        <div style="display:flex; justify-content:center; width:50vw; align-items: center; border:2px solid #adb5bd; background-color:#FAFAFA">
            <div style="text-align:center; padding: 10px;">
                <pre id="code_snippet"><code>${codeBody}</span></code></pre>
                <span id="load_date" th:utext="${date}"></span>
            </div>
        </div>
     </div>

    <br>
    <div style="display: flex; flex-direction:row; justify-content:space-evenly; align-items:baseline; padding: 0.5rem 1rem;width:300px;">
        <h4 style="font-size:25px; font-weight:100px" class="navbar-brand">Comments</h4>
        <div style="border-left: 1px solid #adb5bd;height: 50px; align-self:center;"></div>
        <button style="text-align:center; background-color:#6f42c1;" class="btn btn-primary">New</button>
    </div>
    <div hidden class="form_container">
            <form action="#" id="form_sheet">
                <div class="form-group">
                    <label class="form-row"> Enter a comment for this code:</label>
                        <textarea class="form-control"th:utext="${textArea}"></textarea>
                </div>
                <div class="form-group form-row bdiv">
                    <button style="background-color:#6f42c1;" class="btn btn-primary form-control" type="submit" onclick="send()">Submit</button>
                </div>
            </form>
         </div>
    <div style="padding: 1rem 4rem">
        <table style="width:1000px;">
               <#list comList as comment>
                    <tr>
                         <td style="padding-bottom: 20px; border-bottom: 1px solid #adb5bd; width:500px;">${comment.comment}</td>
                    </tr>
               </#list>
        </table>
    </div>

    <script>

        let viewsSpan = document.getElementById("viewsP");
        let timeSpan = document.getElementById("timeP");

        let views = document.getElementById("viewsP").querySelector("span");
        let time = document.getElementById("timeP").querySelector("span");

        if (parseInt(time.innerText) > 0) {
            timeSpan.removeAttribute("hidden");
            time.setAttribute("id", "time_restriction");
        }
        if (parseInt(views.innerText) > 0) {
            viewsSpan.removeAttribute("hidden");
            views.setAttribute("id", "views_restriction");
        }

        console.log(views);
        console.log(time.innerText);
        console.log(viewsP);
        console.log(timeP);
    </script>

    <!--    bootstrap-->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
