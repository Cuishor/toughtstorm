<%@ page import="cui.toughtstorm.core.dao.view.User" %>
<%@ page import="cui.toughtstorm.core.dao.ToughtStormDAOImpl" %>
<%@ page import="cui.toughtstorm.core.dao.view.Tought" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>--%>
<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>--%>
<%
    User user = (User) session.getAttribute("user");
    List<Tought> toughtList = new ToughtStormDAOImpl().getPosts();
    int noOfPosts = 0;
    for(Tought t : toughtList) {
        if(t.getUserId().equals(user.getId())) noOfPosts++;
    }
    String noOfPostsString = String.valueOf(noOfPosts);
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>ToughtStorm::Home</title>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<script>

    $( document ).ready( function () {


    });

</script>
<style>

    textarea:hover,
    input:hover,
    textarea:active,
    input:active,
    textarea:focus,
    input:focus,
    button:focus,
    button:active,
    button:hover,
    label:focus,
    .btn:active,
    .btn.active
    {
        outline:0px !important;
        -webkit-appearance:none;
        box-shadow: none !important;
    }

</style>
<body>
    <div class="font-weight-bold mx-auto my-5" style="width:60rem;">
        <h1>ToughtStorm</h1>
    </div>
    <div class="row mx-auto px-3">
        <div class="col" style="width:auto%;">
            <div class="card shadow-sm pl-2
                 rounded mx-3 mt-3" style="width:15rem; height:22rem;">
                <div class=" pb-3 pt-4 font-weight-light">
                    <h2><%= user.getUsername() %></h2>
                </div>
                <div class="px-2 pb-3">
                    <div class="font-italic">
                        <%="\" " + user.getShortBio() + " \""%>
                    </div>
                </div>
                <div class="px-2 py-2">
                    Joined Earth: <%=user.getDob()%>
                </div>
                <div class="px-2 py-2">
                    <%=user.getGender()%>
                </div>
                <div class="px-2 py-2">
                    <div>No. posts: <%=noOfPostsString%></div>
                    <div>Score: <%= user.getScore() %></div>
                </div>
                <div class="float-left">
                    <form action="goToIndex" method="post" id="goToIndex">
                    </form>
                    <a href="#" class="card-link ml-2" id="logOut"
                       onclick="$('#goToIndex').submit();">Log Out</a>
                </div>
            </div>
        </div>
        <div class="col" style="width:10%"></div>
        <div class="col" style="width:30%;">
            <div class="card mx-auto my-4 px-2 shadow-sm" style="width:30rem; height:15rem;">
                <div class="font-weight-light pt-3 pb-1 px-4">
                    Share a tought!
                </div>
                <form action="postTought" method="post">
                    <div class="form-group">
                        <textarea name="post-toughtContent-textarea" id="post-toughtContent-textarea" rows="4"
                                  class="form-control-plaintext border border-white px-3 pb-3"></textarea>
                    </div>
                    <div class="pb-4 pr-4">
                        <button type="submit" value="submit" name="submit"
                                class="btn btn-outline-dark border border-dark float-right">Post it!</button>
                    </div>
                </form>
            </div>
            <div class="mx-auto my-4">
                <form id="toughtContainer" action="updatePoints" method="post">
                    <input type="hidden" name="toughtId" id="toughtId" value="" />
                    <input type="hidden" name="operation" id="operation" value="" />
                </form>
                <form id="toughtEraser" action="deleteTought" method="post">
                    <input type="hidden" name="toughtIdForDel" id="toughtIdForDel" value="" />
                </form>
                <% for(Tought t : toughtList) { %>
                <div class="card text-left my-4" style="width:30rem;height:13rem">
                    <div class="card-body">
                        <h5 class="card-title"><%=t.getUsername()%></h5>
                        <p class="card-text"><%=t.getContent()%></p>
                    </div>
                    <div class="card-body">
                        <% if(user.getId().equals(t.getUserId())) { %>
                        <div class="float-left mt-4">
                            <a href="#delete" class="card-link mx-2" id="delete<%=t.getId()%>"
                                    onclick="document.getElementById('toughtIdForDel').value='<%=t.getId()%>';
                                            console.log('pressed delete on id <%=t.getId()%>');
                                            $('#toughtEraser').submit();">Delete</a>
                        </div>
                        <% } %>
                        <div class="float-right mt-4">
                            <a href="#upvote" class="card-link mr-2" id="upvote<%=t.getId()%>"
                               onclick="document.getElementById('toughtId').value='<%=t.getId()%>';
                                       document.getElementById('operation').value='+';
                                       console.log('pressed + on id <%=t.getId()%>');
                                       $('#toughtContainer').submit();">+</a>
                            <%=t.getPoints()%>
                            <a href="#downvote" class="card-link ml-2" id="downvote<%=t.getId()%>"
                               onclick="document.getElementById('toughtId').value='<%=t.getId()%>';
                                       document.getElementById('operation').value='-';
                                       console.log('pressed - on id <%=t.getId()%>');
                                       $('#toughtContainer').submit();">-</a>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
        <div class="col" style="width:10%;"></div>
        <div class="col" style="width:auto;">
            <div class="card shadow-sm pl-2
                 rounded mx-3 mt-3" style="width:15rem; height:22rem;">
                    <div class="text-left font-weight-light px-3 py-2">Order By:</div>
                    <select class="custom-select px-3 py-1 mx-auto" style="width:11rem;">
                        <option value="most-relevant">Most relevant</option>
                        <option value="newest">Newest</option>
                        <option value="" selected="selected">Select</option>
<%--                        <option value="mine-first"></option>--%>
                    </select>
            </div>

        </div>
    </div>



</body>
</html>
