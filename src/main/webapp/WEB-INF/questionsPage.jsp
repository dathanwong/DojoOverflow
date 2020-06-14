<!doctype html>
<html lang="en">
    <head>
        <title>Title</title>
        <!-- Required meta tags -->
        <meta charset="ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1><c:out value="${question.question}"/></h1>
            </div>
            <div class="row my-3">
                <h2>Tags: </h2>
                <c:forEach items="${question.tags}" var="tag">
	                <a class="btn btn-outline-secondary mx-2"><c:out value="${tag.subject}"/></a>
                </c:forEach>
            </div>
            <div class="row my-5">
                <div class="col-6">
                    <table class="table table-striped">
                        <thead>
                            <th>Answers</th>
                        </thead>
                        <tbody>
	                        <c:forEach items="${question.answers}" var="answer">
	                            <tr>
	                                <td><c:out value="${answer.answer}"/></td>
	                            </tr>
	                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-6">
                    <form:form action="/answers/${question.id}/new" method="POST" modelAttribute="answer">
                    	<form:errors path="*" class="text-danger"/>
                        <div class="row">
                            <h3>Add your answer:</h3>
                        </div>
                        <div class="row my-2">
                            <div class="col-3">Answer</div>
                            <form:textarea path="answer" class="col-9" name="answer" id="answer" cols="30" rows="10"></form:textarea>
                        </div>
                        <div class="row my-2">
                            <div class="col text-right">
                                <button type="submit" class="btn btn-primary">Answer it!</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>