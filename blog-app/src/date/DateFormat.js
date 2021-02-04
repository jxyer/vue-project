export function formatTime(time) {
	const date = new Date(time)
	const now = Date.now();
	const diff = (now - date) / 1000;
	if (diff < 30) {
		return '刚刚'
	} else if (diff < 3600) {
		return Math.ceil(diff / 60) + '分钟前'
	} else if (diff < 3600 * 24) {
		return Math.ceil(diff / 3600) + '小时前'
	} else if (diff < 3600 * 24 * 2) {
		return '1天前'
	}
	return time
}
