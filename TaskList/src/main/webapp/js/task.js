"use strict";

let addActionUrl = null;
let addActionUrlExcluir = null
function setStatus(url) {
	addActionUrl = url;
}
function setDeleteTask(url) {
	addActionUrlExcluir = url;
}

async function taskEmAndamento(taskId, deadline, text) {
	const data = new URLSearchParams();
	data.set("id", taskId);
	data.set("status", 'Em andamento')
	data.set("deadline", deadline)
	data.set("text", text)
	const response = await fetch(addActionUrl, {
		method: 'POST',
		body: data
	});

	if (response.status == 200) {
		reload();
	}
}
async function taskConcluida(taskId, deadline, text) {
	const data = new URLSearchParams();
	data.set("id", taskId);
	data.set("status", 'Concluida')
	data.set("deadline", deadline)
	data.set("text", text)
	const response = await fetch(addActionUrl, {
		method: 'POST',
		body: data
	});

	if (response.status == 200) {
		reload();
	}
}

async function excluirTask(taskId) {
	const data = new URLSearchParams();
	data.set("id", taskId);
	const response = await fetch(addActionUrlExcluir, {
		method: 'POST',
		body: data
	});

	if (response.status == 200) {
			reload();
	}
}

function reload() {
	location.reload();
}
