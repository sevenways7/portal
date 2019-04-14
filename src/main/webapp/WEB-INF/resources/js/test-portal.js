$(document).ready(function () {
    function createPost(text) {
        $.ajax({
            type: 'POST',
            url: window.location.origin + '/post-service/create-post',
            data: {
                text: text
            },
            success: function () {
                location.reload();
            },
            error: function (e) {
                console.error(JSON.stringify(e));
            }
        });
    }

    function editPost(id, newText) {
        $.ajax({
            type: 'POST',
            url: window.location.origin + '/post-service/edit-post',
            data: {
                id: id,
                newText: newText
            },
            success: function () {
                location.reload();
            },
            error: function (e) {
                console.error(JSON.stringify(e));
            }
        });
    }

    function removePost(id) {
        $.ajax({
            type: 'POST',
            url: window.location.origin + '/post-service/remove-post',
            data: {
                id: id
            },
            success: function () {
                location.reload();
            },
            error: function (e) {
                console.error(JSON.stringify(e));
            }
        });
    }

    // =====

    function createComment(postId, text) {
        $.ajax({
            type: 'POST',
            url: window.location.origin + '/post-service/create-comment-and-add-to-post',
            data: {
                id: postId,
                text: text
            },
            success: function () {
                location.reload();
            },
            error: function (e) {
                console.error(JSON.stringify(e));
            }
        });
    }

    function editComment(id, text) {
        $.ajax({
            type: 'POST',
            url: window.location.origin + '/post-service/edit-comment',
            data: {
                id: id,
                text: text
            },
            success: function () {
                location.reload();
            },
            error: function (e) {
                console.error(JSON.stringify(e));
            }
        });
    }

    function removeComment(commentId) {
        $.ajax({
            type: 'POST',
            url: window.location.origin + '/post-service/remove-comment-from-post',
            data: {
                commentId: commentId
            },
            success: function () {
                location.reload();
            },
            error: function (e) {
                console.error(JSON.stringify(e));
            }
        });
    }

    $(".textarea")
        .css("max-width", "100%")
        .css("height", "150px");

    //returns true if string is Null, UnDefined, Empty or Blank
    var isNullUnDefinedEmptyOrBlank = function (str) {
        var pattern = /^[\s]+$/;
        return (!str || str.size === 0 || pattern.test(str))
    };

    // Manage post dialog.
    var managePostDialog = $("#edit-delete-post-dialog");

    var editPostBtn = $(".manage-post");

    var closePostBtn = $("#close-post-dialog");
    var savePostBtn = $("#save-post-dialog");
    var deletePostBtn = $("#delete-post-dialog");

    editPostBtn.off();
    editPostBtn.click(function () {
        console.log("Editing post.");

        var id = this.id.split("-")[2];
        var text = $("#posted-text-" + id + " p").text();

        console.log("id = " + id);
        console.log("text = " + text);

        //set values to dialog
        $("#post-text").val(text);
        $("#post-id").val(id);
        $("#post-dialog-header").text("Edit or delete post.");

        managePostDialog.css("display", "block");
    });

    closePostBtn.off();
    closePostBtn.click(function () {
        managePostDialog.css("display", "none");
    });

    savePostBtn.off();
    savePostBtn.click(function () {
        console.log("Saving post.");

        var text = $("#post-text").val();
        var id = $("#post-id").val();

        console.log("text = " + text);
        console.log("id = " + id);

        //check if text empty
        if (isNullUnDefinedEmptyOrBlank(text)) {
            alert("Post need to be filled!");
        } else {
            //if ID is empty, create new post.
            if (isNullUnDefinedEmptyOrBlank(id)) {
                console.log("Saving CREATED post.");
                createPost(text);
            } else {
                console.log("Saving EDITED post.");

                editPost(id, text);
            }
        }

        //close dialog box.
        managePostDialog.css("display", "none");

    });

    deletePostBtn.off();
    deletePostBtn.click(function () {
        console.log("Deleting post.");

        var id = $("#post-id").val();

        console.log("id = " + id);

        //if ID is empty, do nothing.
        if (!isNullUnDefinedEmptyOrBlank(id)) {
            removePost(id);
        }

        //close dialog box.
        managePostDialog.css("display", "none");
    });

    //============End Manage post dialog.

    // Manage comment dialog.
    var manageCommentDialog = $("#edit-delete-comment-dialog");

    var editCommentBtn = $(".manage-comment");

    var closeCommentBtn = $("#close-comment-dialog");
    var saveCommentBtn = $("#save-comment-dialog");
    var deleteCommentBtn = $("#delete-comment-dialog");

    editCommentBtn.off();
    editCommentBtn.click(function () {
        console.log("Editing comment.");

        var ids = this.id.split("-");
        var postId = ids[2];
        var commentId = ids[3];

        console.log("postId = " + postId);
        console.log("commentId = " + commentId);

        var text = $("#commented-text-" + postId + "-" + commentId + " p").text();

        console.log("text = " + text);

        //set values to dialog
        $("#comment-text").val(text);

        $("#post-comment-id")
            .attr("post-id", postId)
            .attr("comment-id", commentId);

        $("#comment-dialog-header").text("Edit or delete comment.");

        manageCommentDialog.css("display", "block");
    });

    closeCommentBtn.off();
    closeCommentBtn.click(function () {
        manageCommentDialog.css("display", "none");
    });

    saveCommentBtn.off();
    saveCommentBtn.click(function () {
        console.log("Edit/Save comment.");

        var text = $("#comment-text").val();
        var postId = $("#post-comment-id").attr("post-id");
        var commentId = $("#post-comment-id").attr("comment-id");

        console.log("postId = " + postId);
        console.log("commentId = " + commentId);
        console.log("text = " + text);

        //check if text empty
        if (isNullUnDefinedEmptyOrBlank(text)) {
            alert("Comment need to be filled!");
        } else {
            //if ID is empty, create new post.
            if (isNullUnDefinedEmptyOrBlank(commentId)) {
                console.log("Saving CREATED comment.");

                createComment(postId, text);
            } else {
                console.log("Saving EDITED comment.");

                editComment(commentId, text);
            }
        }

        //close dialog box.
        manageCommentDialog.css("display", "none");
    });

    deleteCommentBtn.off();
    deleteCommentBtn.click(function () {
        console.log("Deleting comment.");

        var commentId =
            $("#post-comment-id").attr("comment-id");

        console.log("commentId =" + commentId);

        if (!isNullUnDefinedEmptyOrBlank(commentId)) {
            removeComment(commentId);
        }

        //close dialog box.
        manageCommentDialog.css("display", "none");
    });

    //============End Manage post dialog.

    $("#create-post-main-window").off()
        .click(function () {
            console.log("Creating post. Activate dialog.");

            $("#post-text").val("");
            $("#post-id").val("");

            $("#post-dialog-header").text("Create post.");

            //show dialog.
            managePostDialog.css("display", "block");
        });

    $(".add-comment-to-post").off()
        .click(function () {
            console.log("Adding comment.");
            $("#comment-text").val("");

            var postId = this.id.split("-")[2];
            console.log("postId = " + postId);

            $("#post-comment-id")
                .attr("post-id", postId)
                .attr("comment-id", "");

            $("#comment-dialog-header").text("Create comment.");

            console.log("Activating dialog.");
            //show dialog.
            manageCommentDialog.css("display", "block");
        });
});