<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	function toggleAvatarMenu() {
		document.getElementById('avatarDropdown').classList.toggle('show');
	}
	window.addEventListener('click', function(e) {
		if (!e.target.closest('.avatar-menu')) {
			var dd = document.getElementById('avatarDropdown');
			if (dd) dd.classList.remove('show');
		}
	});
</script>