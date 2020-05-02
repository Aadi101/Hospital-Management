$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	{
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
}
);

//SAVE======================================
$(document).on("click", "#btnSave", function(event)
{
	//clear alerts-----------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	//Form validation-------------------------
	var status = validationHospitalForm();
	if(status != true)
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	//If valid-------------------------
	$("#formHospital").submit();
});

//UPDATE==============================================
$(document).on("click", ".btnUpdate",function(event)
{
	$("#hidHosIDSave").val($(this).closest("tr").find('#hidHosIDUpdate').val());
	$("#hosID").val($(this).closest("tr").find('td:eq(0)').text());
	$("#hosName").val($(this).closest("tr").find('td:eq(1)').text());
	$("#hosMail").val($(this).closest("tr").find('#td:eq(2)').text());
	$("#prov").val($(this).closest("tr").find('td:eq(3)').text());
	$("#city").val($(this).closest("tr").find('td:eq(4)').text());
	$("#posCode").val($(this).closest("tr").find('td:eq(5)').text());
	$("#phnNum").val($(this).closest("tr").find('td:eq(6)').text());
	$("#er").val($(this).closest("tr").find('td:eq(7)').text());
	$("#surg").val($(this).closest("tr").find('td:eq(8)').text());
	$("#xray").val($(this).closest("tr").find('td:eq(9)').text());
	$("#lab").val($(this).closest("tr").find('td:eq(10)').text());
	$("#gov").val($(this).closest("tr").find('td:eq(11)').text());
});

//CLIENT-MODEL============================================
function validationHospitalForm()
{
	//ID
	if($("#hosID").val().trim() == "")
	{
		return "Insert Hospital ID.";
	}
	
	//ID
	if($("#hosName").val().trim() == "")
	{
		return "Insert Hospital Name.";
	}
	
	//ID
	if($("#hosMail").val().trim() == "")
	{
		return "Insert Hospital Mail.";
	}
	
	//ID
	if($("#prov").val().trim() == "")
	{
		return "Insert Province.";
	}
	
	//ID
	if($("#city").val().trim() == "")
	{
		return "Insert City.";
	}
	
	//ID
	if($("#posCode").val().trim() == "")
	{
		return "Insert PostalCode.";
	}
	
	//ID
	if($("#phnNum").val().trim() == "")
	{
		return "Insert Phone Number.";
	}
	
	var postalCode = $("#posCode").val().trim();
	if(!$.isNumeric(postalCode))
	{
		return "Insert a Numerical value!"
	}
	
	return true;
}