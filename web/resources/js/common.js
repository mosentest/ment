function confirmCall(url, title, message, description) {
	$('#delete-modal #modal-title').html(title);
	$('#delete-modal #modal-message').html(message);
	$('#delete-modal #modal-perform').click(function() {
		$.ajax({
			url: url,
			type: 'POST',
			success: function(data, status, xhr) {
				window.scrollTo(0, 0);
				if (data.success) {
					showAlert('confirm', data.message);
				} else {
					showAlert('warning', data.message);
				}
			},
			error: function(xhr, statux, error) {
				window.scrollTo(0, 0);
				showAlert('warning', error);
			}
		});
	});
	$('#delete-modal').modal('show');
}

function ajaxSubmit(id) {
	var form = $('#' + id);
	$.ajax({
		url: form.attr('action'),
		type: form.attr('method'),
		data: form.serialize(),
		success: function(data, status, xhr) {
			window.scrollTo(0, 0);
			if (data.success) {
				showAlert('confirm', data.message);
			} else {
				showAlert('warning', data.message);
			}
		},
		error: function(xhr, statux, error) {
			window.scrollTo(0, 0);
			showAlert('warning', error);
		}
	});
}

function clearForm(id) {
	$('#' + id).find('input:text, input:password, input:file, select, textarea').val('');
    $$('#' + id).find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');
}

function showAlert(type, message) {
	var dialog = $('#response-message');
	dialog.removeClass();
	message = "<strong>" + message + "</strong>";
	var container = "";
	dialog.addClass("alert");
	if (type == 'info') {
		dialog.addClass("alert-info");
	} else if (type == 'success') {
		dialog.addClass("alert-success");
	} else if (type == 'warning') {
		dialog.addClass("alert-warning");
	} else {
		dialog.addClass("alert-danger");
	}
	dialog.html(message);
	var left = (($(window).width() - dialog.outerWidth()) / 2) + $(window).scrollLeft();
	dialog.css({left : left + 'px'});
	dialog.slideDown(2000).delay(2000).slideUp(2000);
}

function openModal(id) {
	$('#' + id).modal('show');
}