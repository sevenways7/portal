$.ajax({
    type: 'POST',
    url: window.location.origin + '/test-rest/test',
    data: {
        text: "Hello"
    },
    success: function (data) {
        console.log("Success. Data = ");
        console.log(data);
    },
    error: function (e) {
        console.log("Error.");
        console.error(JSON.stringify(e));
    }
});