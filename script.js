// Get elements for adding modules
const addQuestionBtn = document.getElementById('addQuestionBtn');
const moduleOptions = document.getElementById('moduleOptions');
const addModuleBtn = document.getElementById('addModuleBtn');
const addModuleModal = document.getElementById('addModuleModal');
const closeBtns = document.querySelectorAll('.close');
const moduleForm = document.getElementById('moduleForm');
const moduleNameInput = document.getElementById('moduleName');
const existingModulesList = document.getElementById('existingModulesList');
const addTopicBtn = document.getElementById('addTopicBtn');
const addTopicModal = document.getElementById('addTopicModal');
const closeTopicModalBtn = document.querySelector('#addTopicModal .close');
const topicForm = document.getElementById('topicForm');
const topicNameInput = document.getElementById('topicName');
const topicsContainer = document.getElementById('topicsContainer');
const questionTypeModal = document.getElementById('questionTypeModal');
const singleChoiceBtn = document.getElementById('singleChoiceBtn');
const multipleChoiceBtn = document.getElementById('multipleChoiceBtn');
const trueFalseBtn = document.getElementById('trueFalseBtn');

// Function to close the modal for adding modules
function closeModal() {
    addModuleModal.style.display = 'none';
}

// Function to close the add topic modal
function closeTopicModal() {
    addTopicModal.style.display = 'none';
}

// Function to close the question type modal
function closeQuestionTypeModal() {
    questionTypeModal.style.display = 'none';
}

// Event listener for clicking "Add Question" button
addQuestionBtn.addEventListener('click', () => {
    addQuestionBtn.style.display = 'none'; // Hide the "Add Question" button
    moduleOptions.style.display = 'block'; // Show module options
});

// Event listener for clicking "Add Module" button
addModuleBtn.addEventListener('click', () => {
    addModuleModal.style.display = 'block'; // Show the add module modal
});

// Event listeners for closing modals
closeBtns.forEach(closeBtn => {
    closeBtn.addEventListener('click', () => {
        closeBtn.closest('.modal').style.display = 'none';
    });
});

// Function to add a new module
function addModule(moduleName) {
    // Create a new module button
    const moduleButton = document.createElement('button');
    moduleButton.textContent = moduleName;
    moduleButton.classList.add('module-button');
    existingModulesList.appendChild(moduleButton);

    // Add click event listener to the new module button
    moduleButton.addEventListener('click', () => {
        // Clear previous active state
        existingModulesList.querySelectorAll('.module-button').forEach(button => {
            button.classList.remove('active');
        });
        // Set current button as active
        moduleButton.classList.add('active');
        // Show the "Add Topic" button
        addTopicBtn.style.display = 'block';
    });
}

// Event listener for submitting the module form
moduleForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent form submission
    const moduleName = moduleNameInput.value.trim();
    if (moduleName !== '') {
        // Add the new module
        addModule(moduleName);
        // Clear the input field
        moduleNameInput.value = '';
        // Close the modal
        closeModal();
    } else {
        alert('Please enter a module name!');
    }
});

// Event listener for clicking on the "Add Topic" button
existingModulesList.addEventListener('click', (event) => {
    if (event.target.classList.contains('module-button')) {
        addTopicBtn.style.display = 'block'; // Show the "Add Topic" button
    }
});

// Event listener for clicking on the "Add Topic" button
addTopicBtn.addEventListener('click', () => {
    addTopicModal.style.display = 'block'; // Show the add topic modal
});

// Event listener for submitting the topic form
topicForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent form submission
    const topicName = topicNameInput.value.trim();
    if (topicName !== '') {
        // Add the new topic
        addTopic(topicName);
        // Clear the input field
        topicNameInput.value = '';
        // Close the modal
        closeTopicModal();
    } else {
        alert('Please enter a topic name!');
    }
});

// Function to add a new topic
function addTopic(topicName) {
    // Create a new topic button
    const topicButton = document.createElement('button');
    topicButton.textContent = topicName;
    topicButton.classList.add('topic-button');
    topicsContainer.appendChild(topicButton);
    
    // Add click event listener to the new topic button
    topicButton.addEventListener('click', () => {
        // Show the question type modal
        questionTypeModal.style.display = 'block';
    });
}

// Event listeners for closing modals
closeBtns.forEach(closeBtn => {
    closeBtn.addEventListener('click', () => {
        closeBtn.closest('.modal').style.display = 'none';
    });
});

// Event listener for clicking on "Single Choice" button
singleChoiceBtn.addEventListener('click', () => {
    // Here you can add functionality to handle single choice question
    alert('Single Choice question type selected');
    closeQuestionTypeModal();
});

// Event listener for clicking on "Multiple Choice" button
multipleChoiceBtn.addEventListener('click', () => {
    // Here you can add functionality to handle multiple choice question
    alert('Multiple Choice question type selected');
    closeQuestionTypeModal();
});

// Event listener for clicking on "True/False" button
trueFalseBtn.addEventListener('click', () => {
    // Here you can add functionality to handle true/false question
    alert('True/False question type selected');
    closeQuestionTypeModal();
});


// Event listener for clicking on the "Single Choice" button
document.getElementById('singleChoiceBtn').addEventListener('click', () => {
    // Show the question form modal
    document.getElementById('questionFormModal').style.display = 'block';
});

// Event listener for submitting the question form
document.getElementById('questionForm').addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent form submission

    // Get the values from the form fields
    const question = document.getElementById('question').value.trim();
    const correctAnswer = document.getElementById('correctAnswer').value.trim();
    const wrongAnswers = document.getElementById('wrongAnswers').value.trim().split(',');
    const hint = document.getElementById('hint').value.trim();

    // Log the values for testing (you can replace this with your desired logic)
    console.log('Question:', question);
    console.log('Correct Answer:', correctAnswer);
    console.log('Wrong Answers:', wrongAnswers);
    console.log('Hint:', hint);

    // Clear the form fields
    document.getElementById('question').value = '';
    document.getElementById('correctAnswer').value = '';
    document.getElementById('wrongAnswers').value = '';
    document.getElementById('hint').value = '';

    // Close the question form modal
    document.getElementById('questionFormModal').style.display = 'none';
});

// Event listener for clicking on the "Multiple Choice" button
document.getElementById('multipleChoiceBtn').addEventListener('click', () => {
    // Show the multiple choice question form modal
    document.getElementById('multipleChoiceFormModal').style.display = 'block';
});

// Event listener for submitting the multiple choice question form
document.getElementById('multipleChoiceForm').addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent form submission

    // Get the values from the form fields
    const mcQuestion = document.getElementById('mcQuestion').value.trim();
    const mcCorrectChoices = document.getElementById('mcCorrectChoices').value.trim().split(',');
    const mcWrongChoices = document.getElementById('mcWrongChoices').value.trim().split(',');
    const mcHint = document.getElementById('mcHint').value.trim();

    // Log the values for testing (you can replace this with your desired logic)
    console.log('Multiple Choice Question:', mcQuestion);
    console.log('Correct Choices:', mcCorrectChoices);
    console.log('Wrong Choices:', mcWrongChoices);
    console.log('Hint:', mcHint);

    // Clear the form fields
    document.getElementById('mcQuestion').value = '';
    document.getElementById('mcCorrectChoices').value = '';
    document.getElementById('mcWrongChoices').value = '';
    document.getElementById('mcHint').value = '';

    // Close the multiple choice question form modal
    document.getElementById('multipleChoiceFormModal').style.display = 'none';
});


// Event listener for clicking on the "True/False" button
document.getElementById('trueFalseBtn').addEventListener('click', () => {
    // Show the true/false question form modal
    document.getElementById('trueFalseFormModal').style.display = 'block';
});

// Event listener for submitting the true/false question form
document.getElementById('trueFalseForm').addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent form submission

    // Get the values from the form fields
    const tfQuestion = document.getElementById('tfQuestion').value.trim();
    const tfAnswer = document.getElementById('tfAnswer').value;

    // Log the values for testing (you can replace this with your desired logic)
    console.log('True/False Question:', tfQuestion);
    console.log('Correct Answer:', tfAnswer);

    // Clear the form fields
    document.getElementById('tfQuestion').value = '';
    document.getElementById('tfAnswer').value = 'true';

    // Close the true/false question form modal
    document.getElementById('trueFalseFormModal').style.display = 'none';
});

// Function to update the display with the saved questions
function updateQuestionList() {
    const questionsContainer = document.getElementById('questionsContainer');
    questionsContainer.innerHTML = ''; // Clear the container

    // Iterate over the questions array and create HTML elements for each question
    questions.forEach((question, index) => {
        const questionElement = document.createElement('div');
        questionElement.classList.add('question');
        questionElement.innerHTML = `
            <p><strong>Question ${index + 1}:</strong> ${question.question}</p>
            <p><strong>Type:</strong> ${question.type}</p>
        `;
        
        if (question.type === 'True/False') {
            questionElement.innerHTML += `<p><strong>Answer:</strong> ${question.answer ? 'True' : 'False'}</p>`;
        } else if (question.type === 'Single Choice' || question.type === 'Multiple Choice') {
            questionElement.innerHTML += `<p><strong>Options:</strong> ${question.options.join(', ')}</p>`;
            questionElement.innerHTML += `<p><strong>Correct Answer:</strong> ${question.correctAnswer}</p>`;
        }

        questionsContainer.appendChild(questionElement);
    });
}

updateQuestionList();
