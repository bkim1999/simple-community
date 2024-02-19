const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        let id = document.getElementById('post-id').value;
        fetch(`/api/posts/${id}`, {
		    method: 'DELETE',
		}).then(response => {
		    if (!response.ok) {
		        throw new Error(`HTTP error! Status: ${response.status}`);
		    }
		})
		.then(data => {
		    alert('삭제 완료');
		    window.location.replace('/posts');
		})
		.catch(error => {
		    console.error('Fetch Error:', error);
		});
	});
}

const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    modifyButton.addEventListener('click', function () {
        
        let id = document.getElementById('post-id').value;
        
        fetch(`/api/posts/${id}`, {
            method: 'PUT',
            headers: {
				"Content-type": "application/json"
			},
			body: JSON.stringify({
				title: document.getElementById('title').value,
				content: document.getElementById('content').value
			})
        })
        .then(() => {
            alert('수정 완료');
            location.replace('/posts/' + id);
        });
        
    });
}

const createButton = document.getElementById('create-btn');

if (createButton) {
    createButton.addEventListener('click', function () {
        
        fetch('/api/posts', {
            method: 'POST',
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
        .then(response => response.json())
        .then(data => {
            const newPostId = data.id;
            alert('글 생성 완료');
            location.replace('/posts/' + newPostId);
        })
        .catch(error => {
            console.error('Error creating post:', error);
        });
        
    });
}

