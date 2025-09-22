const types = ['números', 'palavras', 'cores'];
let currentSequence = [];
let currentType = "";

let history = { total: 0, correct: 0 };

// Normaliza texto removendo acentos e colocando em minúsculo
function normalizeText(text) {
  return text.normalize('NFD').replace(/[\u0300-\u036f]/g, '').toLowerCase();
}

function startChallenge() {
  // Reset geral
  const feedback = document.getElementById('feedback');
  const inputEl = document.getElementById('userInput');
  const checkBtn = document.getElementById('checkBtn');
  const countdownEl = document.getElementById('countdown');
  const sequenceEl = document.getElementById('sequence');

  feedback.textContent = '';
  feedback.className = '';
  inputEl.value = '';
  inputEl.disabled = true;
  checkBtn.disabled = true;
  countdownEl.textContent = '';
  sequenceEl.textContent = '';

  // Pega dificuldade
  const difficulty = parseInt(document.getElementById('difficulty').value);

  // Escolhe tipo de desafio aleatório
  currentType = types[Math.floor(Math.random() * types.length)];
  document.getElementById('challenge-type').textContent = `Desafio: ${currentType}`;

  // Gera sequência
  switch (currentType) {
    case 'números':
      currentSequence = Array.from({length: difficulty}, () => Math.floor(Math.random() * 10));
      break;
    case 'palavras':
      const palavras = ['casa', 'sol', 'livro', 'carro', 'verde', 'gato', 'mesa', 'flor', 'tempo', 'vento'];
      currentSequence = shuffleArray(palavras).slice(0, difficulty);
      break;
    case 'cores':
      const cores = ['vermelho', 'azul', 'verde', 'amarelo', 'preto', 'branco', 'roxo', 'laranja', 'rosa', 'cinza'];
      currentSequence = shuffleArray(cores).slice(0, difficulty);
      break;
  }

  // Exibe sequência por 4 segundos
  sequenceEl.textContent = currentSequence.join(' – ');

  setTimeout(() => {
    sequenceEl.textContent = '';
    startCountdown(3);
  }, 4000);
}

function startCountdown(seconds) {
  const countdownEl = document.getElementById('countdown');
  const inputEl = document.getElementById('userInput');
  const checkBtn = document.getElementById('checkBtn');

  countdownEl.textContent = `Aguarde: ${seconds}`;

  const interval = setInterval(() => {
    seconds--;
    if (seconds > 0) {
      countdownEl.textContent = `Aguarde: ${seconds}`;
    } else {
      clearInterval(interval);
      countdownEl.textContent = "Digite agora!";
      inputEl.disabled = false;
      checkBtn.disabled = false;
      inputEl.focus();
      inputEl.select();
    }
  }, 2000);
}

function checkAnswer() {
  const feedback = document.getElementById('feedback');
  const inputEl = document.getElementById('userInput');
  const checkBtn = document.getElementById('checkBtn');

  let userInputRaw = inputEl.value.trim();
  if (!userInputRaw) {
    feedback.textContent = "⚠️ Por favor, digite alguma resposta.";
    feedback.style.color = "#f59e0b"; // laranja
    feedback.className = 'orange';
    return;
  }

  const userAnswer = normalizeText(userInputRaw).split(/[\s,.;–-]+/);
  const correctAnswer = currentSequence
    .slice()
    .reverse()
    .map(item => normalizeText(item.toString()));

  if (arraysEqual(userAnswer, correctAnswer)) {
    feedback.textContent = "✅ Correto! Excelente!";
    feedback.style.color = "#16a34a"; // verde
    feedback.className = 'green';
  } else {
    feedback.textContent = `❌ Errado! Correto seria: ${correctAnswer.join(' – ')}`;
    feedback.style.color = "#dc2626"; // vermelho
    feedback.className = 'red';
  }

  // Atualiza histórico
  history.total++;
  if (arraysEqual(userAnswer, correctAnswer)) {
    history.correct++;
  }
  saveHistory();
  updateHistoryUI();

  inputEl.disabled = true;
  checkBtn.disabled = true;
}

function shuffleArray(arr) {
  return arr.sort(() => Math.random() - 0.5);
}

function arraysEqual(a, b) {
  return a.length === b.length && a.every((val, i) => val === b[i]);
}

// Histórico localStorage

function saveHistory() {
  localStorage.setItem('memoryChallengeHistory', JSON.stringify(history));
}

function loadHistory() {
  const saved = localStorage.getItem('memoryChallengeHistory');
  if (saved) {
    history = JSON.parse(saved);
  }
  updateHistoryUI();
}

function updateHistoryUI() {
  document.getElementById('totalCount').textContent = history.total;
  document.getElementById('correctCount').textContent = history.correct;
}

// Resetar histórico
document.getElementById('resetHistoryBtn').addEventListener('click', () => {
  history = { total: 0, correct: 0 };
  saveHistory();
  updateHistoryUI();

  const feedback = document.getElementById('feedback');
  feedback.textContent = 'Histórico resetado.';
  feedback.style.color = '#475569';
  feedback.className = '';
});

// Inicializa
window.onload = () => {
  loadHistory();
};