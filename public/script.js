$(document).ready(function () {

const route = "http://localhost:8080/api/v1/"
const routeChats = route + "chats/"
const routePersonnes = route + "personnes/"
//uniquement pour delete & update
const routeComments = route + "commentaires/"

const body = $("#page-content")
const title = $("#page-nom")


var userName = null
var userId = null
var users = null 

var info = null 

// permet de faire un post avec spring
$.postJSON = function(url, data, callback) {
    return jQuery.ajax({
    headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
    'type': 'POST',
    'url': url,
    'data': JSON.stringify(data),
    'dataType': 'json',
    'success': callback
    })
}
$.putJSON = function(url, data, callback) {
    return jQuery.ajax({
    headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
    'type': 'PUT',
    'url': url,
    'data': JSON.stringify(data),
    'dataType': 'json',
    'success': callback
    })
}
$.patchJSON = function(url, data, callback) {
    return jQuery.ajax({
    headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json',

    },
    'type': 'PATCH',
    'url': url,
    'data': JSON.stringify(data),
    'dataType': 'json',
    'success': callback
    })
}


//! Démarrage
displayUsers()

$("header").on("click", function () {
    displayUsers()
})

$.get(routePersonnes, function (data) {
    users = data
})

$('#connect-button').on("click", function () {
    let tempUser = $('#connect-input').val()
    for (let i in users) {
        if (tempUser == users[i].pseudo) {
            userId = users[i].id
            userName = tempUser
            connect()
        }
    }
    //Si l'utilisateur n'est pas dans la base de données on le créé
    if (userName === null) {
        $.postJSON(routePersonnes, {pseudo: tempUser}, async function(data) {
            userName = tempUser
            userId = data.id
        }).then(response => {
            //une fois la requête accomplie on met a jour la page
            alert("Votre compté a été créé !")
            displayUsers()
            connect()
        })
    }
})

$("#go-users").on("click", function () {
    displayUsers()
})

$("#go-cats").on("click", function () {
    displayCats()
})

function disconnect() {
    userName = null
    userId = null
    $('#connected-user').remove()
    $('#disconnect').remove()
    $('#update-user').remove()
    $('#connect-input').show()
    $('#connect-button').show()
}

function connect() {
    $('#connect-input').hide()
    $('#connect-button').hide()
    let $actualUser = $("<div>", {id: "connected-user"}).append("Connecté en tant que: " + userName)
    $('#user-part').append($actualUser)
    $('#user-part').append($("<button>", {id: "disconnect"}).append("Me déconnecter"))  
    $('#user-part').append($("<button>", {id: "update-user"}).append("Éditer"))
    
    var $form = form({id: 'update-user', label: 'Modifier Nom'}, [{id: 'user-name', label: 'Nom'}], [{id: 'cancel', label: 'Annuler'},{id: 'update-user', label: 'Mettre à jour'}])
    $('#user-part').append($form)

    $('#connected-user').on("click", function () {displayUser(userId)})
    $('#disconnect').on("click", function() {disconnect()})
    $('#update-user').on("click", function () {$('#update-user-form').show()})
    $('#btn-cancel').on("click", function () {$('#update-user-form').hide()})
    $('#btn-update-user').on("click", function () {
        $.putJSON(
            routePersonnes + userId,
            {
                pseudo: $('#input-user-name').val(),
                chats: [],
                commentaires: []
            },
            function(data) {}).then(response => {
            //changer le nom affiché
            userName = $('#input-user-name').val()
            $('#connected-user').html("Connecté en tant que: " + userName)
            $('#update-user-form').hide()
            displayUsers()
        })
    })
    displayUsers()
}

function form(title, inputs, buttons) {
    var $form = $("<div>", {id: title.id + '-form', class: 'form'})
    $form.append($('<h2>').append(title.label))
    
    let $inputs = $('<div>', {id: 'form-inputs'})
    for (i in inputs) {
        $inputs.append($('<strong>').append(inputs[i].label))
        $inputs.append($('<input>', {id: 'input-' + inputs[i].id, type: 'texte'}))
    }
    $form.append($inputs)
    let $buttons = $('<div>', {id: 'form-buttons'})
    
    for (btn in buttons) {
        $buttons.append($('<button>', {id: 'btn-' + buttons[btn].id}).append(buttons[btn].label))
    }
    $form.append($buttons)
    return $form
}

function displayUsers () {
    $.get(routePersonnes, function (data) {
        users = data
        var $boxes = $("<div>", {id: "boxes-users", class: "boxes"})
        $.each(data, function(i, u) {
            let tempUserName = u.pseudo
            let tempId = u.id

            var $boxUser = $("<div>", {id: "box-user-" + u.id, class: "box-user"})
            $boxUser.click(function () {
                let id = event.target.id
                id = id.split("-")
                displayUser(id[2])
            })
            $boxUser.append($("<div>", {id: "name-user-" + u.id, class: "box-name"}).append(tempUserName))
            
            $.get(routePersonnes + tempId, function (data) {
                $boxUser.append($("<div>", {id: "nbCat-user-" + u.id, class: "box-nbCat"}).append("Chats: " + data.chats.length))
                $boxUser.append($("<div>", {id: "nbComm-user-" + u.id, class: "box-nbCom"}).append("Commentaires: " + data.commentaires.length))
            })
            $boxes.append($boxUser)
        })
        cleanPage()
        updatePage("Liste des utilisateurs", $boxes)
    })
}

function displayUser (id) {
    $.get(routePersonnes + id, function (data) {
        var $fullUser = $("<div>", {id: "full-user"})
        if (id == userId) {
            $fullUser.append($("<button>", {id: "delete-user"}).append("Supprimer mon compte"))
        }
        $fullUser.append($("<div>", {class: "boxes-title"}).append("Voici les chats de cet utilisateur"))
        var $listChat = $("<div>", {id: "liste-chats"})
        $.each(data.chats, function (i, cat) {
            let image = cat.photo 
            if (image === null) image = "https://thiscatdoesnotexist.com"
            let $boxCat = $("<div>", {id: "box-cat-" + cat.id, class: "box-cat"})
            $boxCat.append($("<div>", {id: "name-cat-" + cat.id, class: "box-cat-name"}).append(cat.nom), $("<img>", {id: "img-cat-" + cat.id, class: "box-cat-img", src: image}))
            $listChat.append($boxCat)
            // $listChat.append($("<div>", {id: "box-cat-" + cat.id, class: "box-cat"}).append($("<div>", {class: "box-cat-name"}).append(cat.nom), $("<img>", {class: "box-cat-img", src: image})))
            $boxCat.click(function () {
                let id = event.target.id
                id = id.split("-")
                displayCat(id[2])
            })
        })
        if (id == userId) {
            $listChat.append($("<div>", {id: "box-cat-add", class: "box-cat"}).append("+ Ajouter"))
        }
        $fullUser.append($listChat)
        let $form = form({id: 'add-cat', label: 'Ajouter un chat'}, [{id: 'cat-name', label: 'Nom du chat'}, {id: 'cat-desc', label: 'Description'}], [{id: 'cancel-cat', label: 'Annuler'}, {id: 'add-cat', label: 'Ajouter'}])
        $fullUser.append($form)
        cleanPage()
        updatePage(data.pseudo, $fullUser)

        $('#delete-user').on("click", function () {
            $.ajax({
                url: routePersonnes + id,
                type: 'DELETE'
            }).then(response => {
                displayUsers()
                $('#disconnect').click()
            })
        })
        $('#box-cat-add').on("click", function() {$('#add-cat-form').show()})
        $('#btn-cancel-cat').on('click', function() {$('#add-cat-form').hide()})
        $('#btn-add-cat').on("click", function() {
            let nomCat = $("#input-cat-name").val()
            console.log(nomCat)

            let descCat = $("#input-cat-desc").val()
            console.log(descCat)
            
            $.postJSON(routeChats, {
                nom: nomCat,
                description: descCat,
                personne: {
                    id: userId
                }
            }, async function(data) {
                console.log(data)
            }).then(response => {
                displayUser(userId)
            })
        })
    })
    $('#btn-cancel').on("click", function() {
        console.log("cancel")
        $('#add-cat-form').hide()
    })
}

function displayCats () {
    $.get(routeChats, function (data) {
        var $listChat = $("<div>", {id: "liste-chats"})
        $.each(data, function (i, cat) {
            let image = cat.photo 
            if (image === null) image = "https://thiscatdoesnotexist.com"
            let $boxCat = $("<div>", {id: "box-cat-" + cat.id, class: "box-cat"})
            $boxCat.append($("<div>", {id: "name-cat-" + cat.id, class: "box-cat-name"}).append(cat.nom), $("<img>", {id: "img-cat-" + cat.id, class: "box-cat-img", src: image}))
            $listChat.append($boxCat)
            // $listChat.append($("<div>", {id: "box-cat-" + cat.id, class: "box-cat"}).append($("<div>", {class: "box-cat-name"}).append(cat.nom), $("<img>", {class: "box-cat-img", src: image})))
            $boxCat.click(function () {
                let id = event.target.id
                id = id.split("-")
                displayCat(id[2])
            })
        })
        cleanPage()
        updatePage("Liste des chats", $listChat)
    })
}

function displayCat (id) {
    $.get(routeChats + id, function (data) {
        // console.log(data.id)
        var $fullCat = $("<div>", {id: "full-cat"})
        if (data.personne.id == userId) {
            $fullCat.append($("<button>", {id: "delete-cat"}).append("Supprimer mon chat"))
            $fullCat.append($("<button>", {id: "update-cat"}).append("Modifier mon chat"))
            let $form2 = form({id: 'update-cat', label: 'Modifier mon chat'}, [{id: 'cat-name', label: 'Nom du chat'}, {id: 'cat-desc', label: 'Description'}], [{id: 'cancel-cat', label: 'Annuler'}, {id: 'update-cat', label: 'Mettre à jour'}])
            $fullCat.append($form2)
        }

        $fullCat.append($("<div>", {class: "cat-desc"}).append(data.description))
        let image = data.photo 
        if (image === null) image = "https://thiscatdoesnotexist.com"
        $fullCat.append($("<img>", {class: "cat-img", src: image}))
        $fullCat.append($("<div>", {class: "boxes-title"}).append("Commentaires :"))
        var $listComm = $("<div>", {id: "liste-comm"})
        //? commentaires
        $.each(data.commentaires, function (i, comm) {
            // console.log(comm)
            let $comm = $("<div>", {id: "comm-" + comm.id, class: "comm"})
            $comm.append($("<div>", {class: "comm-header"}).append(comm.date + " par " + comm.personne.pseudo))
            $comm.append($("<div>", {class: "comm-body"}).append(comm.message))
            if (comm.personne.id == userId) {
                $comm.append($("<button>", {class: "comm-del", id: "comm-del-" + comm.id}).append("Supprimer"))
                $comm.append($("<button>", {class: "comm-edit", id: "comm-edit-" + comm.id}).append("Éditer"))
            }
            $listComm.append($comm)
        })
        if (userId != null) {$listComm.append($("<button>", {id: "show-add-comm"}).append("Commenter"))}
        $fullCat.append($listComm)

        let $form = form({id: 'add-comm', label: 'Ajouter un commentaire'}, [{id: 'new-comm', label: 'Commentaire'}], [{id: 'cancel-add-comm', label: 'Annuler'}, {id: 'add-comm', label: 'Ajouter'}])
        $fullCat.append($form)
        let $form3 = form({id: 'update-comm', label: 'Modifier mon commentaire'}, [{id: 'update-comm', label: 'Commentaire'}], [{id: 'cancel-update-comm', label: 'Annuler'}, {id: 'update-comm', label: 'Modifier'}])
        $fullCat.append($form3);
        cleanPage()
        updatePage(data.nom + " (maître: " + data.personne.pseudo + ")", $fullCat)

        $('#delete-cat').on("click", function () {
            $.ajax({
                url: routeChats + data.id,
                type: 'DELETE'
            }).then( response => {
                displayUser(userId)
            })
        })
        // update cat
        $('#update-cat').on('click', function () {$('#update-cat-form').show();})
        $('#btn-cancel-cat').on('click', function () {$('#update-cat-form').hide();})
        $('#btn-update-cat').on('click', function () {
            let data = {}
            if ($('#input-cat-name').val() == "" && $('#input-cat-desc').val() != "") {
                data = {description: $('#input-cat-desc').val()}
            } else if ($('#input-cat-name').val() != "" && $('#input-cat-desc').val() == "") {
                data = {nom: $('#input-cat-name').val()}
            } else if ($('#input-cat-name').val() != "" && $('#input-cat-desc').val() != "") {
                data = {nom: $('#input-cat-name').val(), description: $('#input-cat-desc').val()}
            }
            $.patchJSON(
                routeChats + id,
                data,
                function () {}).then(response => {
                    displayCat(id)
                })
        })


        $('.comm-del').on("click", function () {
            let tempId = $(this).attr('id')
            tempId = tempId.split("-")
            $.ajax({
                url: routeComments + tempId[2],
                type: 'DELETE'
            }).then( response => {
                displayCat(id)
            })
        })
        $('#show-add-comm').on("click", function() {$('#add-comm-form').show()})
        $('#btn-cancel-add-comm').on("click", function() {$('#add-comm-form').hide()})
        // add comm
        $("#btn-add-comm").on("click", function () {
            $.postJSON(
                routeChats + id + "/commentaires",
                {
                    message: $("#input-comm").val(),
                    personne: {
                        id: userId
                    }
                },
                async function (data) {
                    console.log(data)
                }).then(response => {
                    displayCat(id)
                })
        })
        //TODO update comm
        let idComm = 0
        $('.comm-edit').on('click', function () {
            $('#update-comm-form').show()
            idComm = $(this).attr('id').split('-')[2]
        })
        $('#btn-cancel-update-comm').on('click', function () {$('#update-comm-form').hide();})
        $('#btn-update-comm').on('click', function () {
            let data = {}
            console.log($('#input-update-comm').val())
            if($('#input-update-comm').val() != "") {data = {message: $('#input-update-comm').val()}}
            console.log(data)
            $.patchJSON(
                routeComments + idComm,
                data, 
                function(data){
                    console.log(data)
                }).then(response => {
                    console.log('ca marche')
                   displayCat(id) 
                })
        })
    })
}

function cleanPage() {
    title.html("")
    body.html("")

}
function updatePage (newTitle, newBody) {
    title.append(newTitle)
    body.append(newBody)
}

})