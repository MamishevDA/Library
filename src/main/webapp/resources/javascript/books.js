/**
 * Created by MamishevDA on 26.07.2016.
 */

// Get the modal
var modal = document.getElementById('addBookModal');

// Get the button that opens the modal
var btn = document.getElementById("openAddBookModal");

var bookLink = document.getElementById("getBookInfo");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function () {
    modal.style.display = "block";
    reset();
}

var reset = function () {
    $("input[name='isn']").val(null);
    $("input[name='author']").val(null);
    $("input[name='title']").val(null);
    $("input[name='id']").val(0);
};
// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
}

/*var startIndex = parseInt($("#bookCount").val(), 10) || 0;
 $("#getNextBooks").click(function () {
 $.get({
 url: "getBooks?startIndex=" + startIndex,
 success: function (items) {
 if (items) {
 for (var i = 0; i < items.length; i++) {
 var row =
 "<tr>" +
 "<td>" + +items[i].id + "</td>" +
 "<td><a href='/book?isn=" + items[i].isn + "'>book</a></td>" +
 "<td>"+ items[i].isn + "</td>" +
 "<td>"+ items[i].title + "</td>" +
 "<td>"+ items[i].author + "</td>" +
 "<td>"+ items[i].takedbyuser + "</td>" +
 "</tr>";
 $("#booksTable tbody").append(row);
 }
 startIndex += items.length;
 }
 }
 })
 });*/
var startIndex = parseInt($("#bookCount").val(), 10) || 0;
$("#getNextBooks").click(function () {
    $.get({
        url: "getBooks?startIndex=" + startIndex,
        success: function (items) {
            if (items) {
                for (var i = 0; i < items.length; i++) {
                    var row =
                        "<tr>" +
                        "<td><a href='#' class='js-isnbtn' data-isn=" + items[i].isn + ">" + items[i].isn + "</a></td>" +
                        "<td>" + items[i].author + "</td>" +
                        "<td>" + items[i].title + "</td>" +
                        "<td><a href='#' class='js-takeBookBtn' data-isn=" + items[i].isn + ">" + items[i].user + "</a></td>" +
                        "<td><a href='#' class='js-deleteBtn' data-isn=" + items[i].isn + ">Удалить книгу</a></td>" +
                        "</tr>";
                    $("#booksTable tbody").append(row);
                }
                startIndex += items.length;
            }
        }
    })
});


$("body").on("click", ".js-isnbtn", function (e) {
    var bookIsn = $(e.target).data("isn");
    $.ajax({
        url: 'getBook?isn=' + bookIsn,
        success: function (result) {
            $("input[name='isn']").val(result.isn);
            $("input[name='author']").val(result.author);
            $("input[name='title']").val(result.title);
            $("input[name='id']").val(result.id);
            modal.style.display = "block";
        }
    });
});

$("body").on("click", ".js-takeBookBtn", function (e) {
    var bookIsn = $(e.target).data("isn");
    $.ajax({
        url: 'takeBookForUser?isn=' + bookIsn,
        success: function () {
            window.location.reload();
        }
    });
});


$("body").on("click", ".js-deleteBtn", function (e) {
    var isn = $(e.target).data("isn");
    if (confirm('Удалить?')) {
        $.ajax({
            url: 'deleteBook?isn=' + isn,
            success: function () {
                window.location.reload();
            }
        });
    } else {
        // Do nothing!
    }
});
