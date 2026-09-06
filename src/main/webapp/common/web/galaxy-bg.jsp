<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="galaxy-bg">
	<div class="glow g1"></div>
	<div class="glow g2"></div>
	<div class="glow g3"></div>
	<div class="planet-ring"></div>
</div>

<script>
(function() {
	var bg = document.getElementById('galaxy-bg');
	var starCount = 80;
	for (var i = 0; i < starCount; i++) {
		var star = document.createElement('div');
		star.className = 'star';
		var size = Math.random() * 2.5 + 1;
		star.style.width = size + 'px';
		star.style.height = size + 'px';
		star.style.top = (Math.random() * 100) + 'vh';
		star.style.left = (Math.random() * 100) + 'vw';
		star.style.animationDelay = (Math.random() * 3) + 's';
		star.style.animationDuration = (2 + Math.random() * 3) + 's';
		bg.appendChild(star);
	}
})();
</script>