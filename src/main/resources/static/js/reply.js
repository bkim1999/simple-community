const deleteButtons = document.getElementsByClassName('reply-delete-btn');

Array.from(deleteButtons).forEach((deleteButton) => {
    deleteButton.addEventListener('click', event => {
        let replyIdElement = deleteButton.closest('.reply').getElementsByClassName('reply-id')[0];
        let id = replyIdElement.value;

        fetch(`/api/replies/${id}`, {
            method: 'DELETE',
        }).then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            alert('삭제 완료');
            window.location.replace('/posts');
        })
        .catch(error => {
            console.error('Fetch Error:', error);
        });
    });
});


const modifyButtons = document.getElementsByClassName('reply-modify-btn');

Array.from(modifyButtons).forEach((modifyButton) => {
    modifyButton.addEventListener('click', function() {
        let replyIdElement = modifyButton.closest('.reply').getElementsByClassName('reply-id')[0];
        let id = replyIdElement.value;

        let content = modifyButton.closest('.reply').getElementsByClassName('content-input')[0].value;

        fetch(`/api/replies/${id}`, {
            method: 'PUT',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                content: content
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(() => {
            alert('수정 완료');
            location.replace('/posts/' + id);
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});


const replyCreateButton = document.getElementById('reply-create-btn');

if (replyCreateButton) {
    replyCreateButton.addEventListener('click', function () {
        let id = document.getElementById('post-id').value;
        fetch(`/api/posts/${id}/replies`, {
            method: 'POST',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                content: document.getElementById('reply-content').value
            })
        })
        .then(response => response.json())
        .then(data => {
            const newPostId = data.id;
            alert('댓글 생성 완료');
            location.reload();
        })
        .catch(error => {
            console.error('Error creating post:', error);
        });
        
    });
}

