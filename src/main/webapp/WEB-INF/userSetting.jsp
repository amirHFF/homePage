<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div>
</div>
<div class="container">
    <div class="row justify-content-center">
        <h1>Account List</h1>
    </div>
</div>
<div class="container mt-5 mb-5">

    <table id="accountTable" class="table table-striped table-hover">
        <thead class="shadow-sm">
        <tr>
            <th scope="col">Username</th>
            <th scope="col">Register time</th>
            <th scope="col">Is Expired</th>
            <th scope="col">Is Enabled</th>
            <th scope="col">Role</th>
            <th scope="col">EDIT</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="exampleModalLabel">Edit Account</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label class="form-label" for="editUsername">User Name</label>
                        <input type="text" id="editUsername" class="form-control"/>
                    </div>
                    <div class="form-group form-check">
                        <input type="checkbox" id="editExpired" class="form-check-input">
                        <label class="form-check-label" for="editExpired">Expired</label>
                        <input type="checkbox" id="editEnabled" class="form-check-input" style="margin-left: 20px">
                        <label class="form-label" for="editEnabled">Enabled</label>
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="oldRole">Current Role</label>
                        <input type="text" id="oldRole" class="form-control"/>
                        <label for="editRole">Add Role</label>
                        <select id="editRole" class="form-control">
                            <option></option>
                            <option>USER</option>
                            <option>ADMIN</option>
                            <option>GUEST</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="editButton">Edit</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="height: 50%" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModal">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-outline">
                        <%--                        <label class="form-label" for="editUsername">User Name</label>--%>
                        <%--                        <input type="password" id="editUsername" class="form-control"/>--%>
                    </div>
                    <%--                    <div class="form-check">--%>
                    <%--                        <input type="checkbox" id="editExpired" class="form-check-input">--%>
                    <%--                        <label class="form-check-label" for="editExpired">Expired</label>--%>
                    <%--                        <input type="checkbox" id="editEnabled" class="form-check-input" style="margin-left: 20px">--%>
                    <%--                        <label class="form-label" for="editEnabled">Enabled</label>--%>
                    <%--                    </div>--%>

                    <%--                    <div class="form-group col-md-4">--%>
                    <%--                        <label for="editRole">Role</label>--%>
                    <%--                        <select id="editRole" class="form-control">--%>
                    <%--                            <option>USER</option>--%>
                    <%--                            <option>ADMIN</option>--%>
                    <%--                            <option>GUEST</option>--%>
                    <%--                        </select>--%>
                    <%--                    </div>--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<script>
	var accountList;
	$(document).ready(function () {
		console.log("enter click")
		$.ajax({
			url: "http://localhost:8080/accounts",
			type: "GET",
			dataType: "json",
			success(accountInfoList) {

				let table = document.getElementById("accountTable");
				accountList = accountInfoList;
				for (let i = 0; i < accountInfoList.length; i++) {
					// var tBody=table.getElementsByTagName("tbody")[0];
					let row = table.insertRow();
					let userNameCell = row.insertCell();
					userNameCell.appendChild(document.createTextNode(accountInfoList[i].username))
					let timeCell = row.insertCell();
					timeCell.appendChild(document.createTextNode(accountInfoList[i].insertTime))
					let expiredCell = row.insertCell();
					expiredCell.appendChild(document.createTextNode(accountInfoList[i].expired))
					let enabledCell = row.insertCell();
					enabledCell.appendChild(document.createTextNode(accountInfoList[i].enabled))
					let roleCell = row.insertCell();
					roleCell.appendChild(document.createTextNode(accountInfoList[i].authorityList));
					let editCell = row.insertCell();
					let editButton = document.createElement('Button');
					const jQueryElement = $(editButton);
					jQueryElement.addClass("btn btn-primary")
					jQueryElement.attr("data-toggle", "modal");
					jQueryElement.attr("data-target", "#myModal");
					jQueryElement.attr("onClick", "setAccountValue(this.id)");
					jQueryElement.attr("id", i);
					// jQueryElement.click(setAccountValue(accountInfoList[jQueryElement.id]));
					editButton.appendChild(document.createTextNode("Edit"));
					editCell.appendChild(editButton);
				}
			}
		})
	});

	function setAccountValue(i) {
		console.log(accountList[i].expired)
		console.log(accountList[i].enabled)
		$('#editUsername').val(accountList[i].username);
		$('#editExpired').prop('checked', accountList[i].expired)
		$('#editEnabled').prop('checked', accountList[i].enabled)
		$('#oldRole').val(accountList[i].authorityList);
		$('#editRole').val("");
	}


	$('#editButton').click(function () {
		console.log(JSON.stringify({
			"username": $('#editUsername').val(),
			"isExpired": $('#editExpired').is(":checked"),
			"isEnabled": $('#editEnabled').is(":checked"),
			"authorityList": $('#editRole').val()
		}));
		let url = "http://localhost:8080/accounts/" + $('#editUsername').val();
		$.ajax({
			url: url,
			type: "PATCH",
			contentType: "application/json",
			data: JSON.stringify({
				"username": $('#editUsername').val(),
				"isExpired": $('#editExpired').is(":checked"),
				"isEnabled": $('#editEnabled').is(":checked"),
				"authority": $('#editRole').val()
			}),
			success(data) {
				alert(data);
			}


		});
	});

	// function loadTable(){
	//
	// 			}
	//         },
	//     })
	// }
	// $('#accountTable').load("http://localhost:8080/accounts", function (data) {
	// 	let table = document.getElementById("accountTable");
	// 	let accountInfoList = JSON.parse(data);
	// 	for (let i = 0; i < accountInfoList.length; i++) {
	// 		// var tBody=table.getElementsByTagName("tbody")[0];
	// 		let row=table.insertRow();
	// 		let userNameCell=row.insertCell();
	// 		userNameCell.appendChild(document.createTextNode(accountInfoList[i].username))
	// 		let timeCell=row.insertCell();
	// 		timeCell.appendChild(document.createTextNode(accountInfoList[i].insertTime))
	// 		let expiredCell=row.insertCell();
	// 		expiredCell.appendChild(document.createTextNode(accountInfoList[i].isExpired))
	// 		let enabledCell=row.insertCell();
	// 		enabledCell.appendChild(document.createTextNode(accountInfoList[i].isEnabled))
	// 		let roleCell=row.insertCell();
	// 		roleCell.appendChild(document.createTextNode(accountInfoList[i].role))
	//
	// 	}
	// });
</script>
<!-- Modal -->
</body>
</html>