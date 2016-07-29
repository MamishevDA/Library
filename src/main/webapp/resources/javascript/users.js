/**
 * Created by MamishevDA on 29.07.2016.
 */
// Get the modal
var modal = document.getElementById('addUserModal');

// Get the button that opens the modal
var btn = document.getElementById("openAddUserModal");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
btn.onclick = function () {
    modal.style.display = "block";
    reset();
}
var reset = function () {
    $("input[name='login']").val(null).attr('readonly', false);
    $("input[name='pwd']").val(null);
    $("input[name='id']").val(0);
};
span.onclick = function () {
    modal.style.display = "none";
}


$("body").on("click", ".js-editBtn", function (e) {
    var userName = $(e.target).data("name");
    $.ajax({
        url: 'getUser?name=' + userName,
        success: function (result) {
            $("input[name='login']").val(result.name).attr('readonly', true);
            $("input[name='pwd']").val(result.pwd);
            $("input[name='id']").val(result.id);
            modal.style.display = "block";
        }
    });
});


$("body").on("click", ".js-deleteBtn", function (e) {
    var name = $(e.target).data("name");
    if (confirm('Удалить?')) {
        $.ajax({
            url: 'deleteUser?name=' + name,
            success: function () {
                window.location.reload();
            }
        });
    } else {
        // Do nothing!
    }
});