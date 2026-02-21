    /**
     * 비동기 API 요청 공통 함수 (Callback 활용)
     */
    const sendApiRequest = async (url, method, data, successCb, errorCb) => {
        try {
            const options = {
                method: method,
                headers: { 'Content-Type': 'application/json' }
            };

            if (data) options.body = JSON.stringify(data);

            const response = await fetch(url, options);
            const result = await response.json();

            if (response.ok) {
                if (successCb) successCb(result);
            } else {
                if (errorCb) errorCb(result.message || "오류가 발생했습니다.");
            }
        } catch (error) {
            console.error("API Error:", error);
            if (errorCb) errorCb("네트워크 오류가 발생했습니다.");
        }
    };


/**
 * 공통 알럿 모달
 * @param {Object} params - {title, message, type, onConfirm}
 */
const showAlert = ({title, message, type = 'success', onConfirm}) => {
    const modalEl = document.getElementById('globalModal');
    const modal = new bootstrap.Modal(modalEl);

    // 1. 아이콘 및 스타일 설정
    const iconEl = document.getElementById('gModalIcon');
    const icons = {
        success: '<i class="bi bi-check-circle-fill text-success"></i>',
        error: '<i class="bi bi-exclamation-octagon-fill text-danger"></i>',
        warning: '<i class="bi bi-exclamation-triangle-fill text-warning"></i>'
    };
    iconEl.innerHTML = icons[type] || icons.success;

    // 2. 텍스트 채우기
    document.getElementById('gModalTitle').innerText = title;
    document.getElementById('gModalMessage').innerText = message;

    // 3. 모달 닫힐 때 이벤트 처리 (콜백)
    if (onConfirm) {
        const handler = () => {
            onConfirm();
            modalEl.removeEventListener('hidden.bs.modal', handler);
        };
        modalEl.addEventListener('hidden.bs.modal', handler);
    }

    modal.show();
};