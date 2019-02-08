/**
-------- Variables --------
*/
//ARRAY LISTS
let People = [[]]
let Car = [[]]

//IMAGES
var FULL = document.createElement("img");
FULL.src = "images/full.png";
var EMPTY = document.createElement("img");
EMPTY.src = "images/empty.png";
var CAR = document.createElement("img");
CAR.src = "images/car.png";
var PEDESTRIAN = document.createElement("img");
PEDESTRIAN.src = "images/pedestrian.png";
var BACKGROUND = document.createElement("img");
BACKGROUND.src = "images/background.jpg";
var MOTORCYCLE = document.createElement("img");
MOTORCYCLE.src = "images/motorcycle.png";
var OTHERBACKGROUND = document.createElement("img");
OTHERBACKGROUND.src = "images/roadTest.png"

//COUNTERS AND INTS
let x = 0
let it = 1;
let curr_count = 0;
let COUNTDOWN = 30;
let playerx = 1;
let score = 0;
let start = 0;
let lives = 3;
let invcible = 0;
var playerOffset = 300;

// BUTTON FUNCTIONS
/**
A function the "easy" button will run when touched, sets the Cooldown or enemy spawn rate to 30 (ticks)
*/
function easySettings() {
    document.getElementById("ezybtn").disabled = true; // Turns off easy button
    document.getElementById("hrdbtn").disabled = true; // Turns off hard button
    COUNTDOWN = 30; // Sets the spawnrate to 30
    start = 1; // Sets our game state to 1 (meaning the game will turn on and run)
}
/**
A function the "hard" button will run when touched, sets the Cooldown or enemy spawn rate to 15 (ticks)
*/
function hardSettings(){
    document.getElementById("hrdbtn").disabled = true; // Turns off easy button
    document.getElementById("ezybtn").disabled = true; // Turns off hard button
    COUNTDOWN = 15; // Sets the spawnrate to 15
    start = 1; // Sets our game state to 1 (meaning the game will turn on and run)
}

function fauna(){
    ctx.beginPath();
    ctx.fillStyle = "#0095DD";
    // Boring Side walk
    ctx.moveTo(650, 0);
    ctx.lineTo(150, 800);
    ctx.moveTo(630, 0);
    ctx.lineTo(0, 800);
    ctx.moveTo(750, 0);
    ctx.lineTo(1250, 800);
    ctx.moveTo(770, 0);
    ctx.lineTo(1400, 800);
    ctx.moveTo(683, 0);
    ctx.lineTo(517, 800);
    ctx.moveTo(716,0)
    ctx.lineTo(883, 800);
    ctx.stroke();
}
function buildings(){
    ctx.drawImage(LEFTBUILDINGS, 0, 0);
    ctx.drawImage(RIGHTBUILDINGS, 600, 0);
}

function gameEnd(){
    start = 2;
}
function fullReset(){
    People = [[]];
    Car = [[]];
    score = 0;
    start = 0;
    playerx = 1;
    lives = 3;
    document.getElementById("hrdbtn").disabled = false;
    document.getElementById("ezybtn").disabled = false;
    requestAnimationFrame(draw);
}
function updatePeople(person) {
    y = person[0];
    if (person[1] == 0){
        ctx.beginPath();
        x = ((y-906.195)*565)/-800
        ctx.drawImage(PEDESTRIAN, x-y/11, y, y/9, y/9);
    }else{
        ctx.beginPath();
        x = ((y+1076.106)*565)/800
        ctx.drawImage(PEDESTRIAN, x-y/110, y, y/9, y/9);
    }
}
function updateCar(car){
    if (car[0] <= 1){
        if (car[1]/1.1 > 600 && playerx == 0){
            if (invcible == 0){
                lives--;
                invcible = 10;
            }

        }
        x = (car[1]-1602.4)*(-333)/800;
        ctx.drawImage(CAR, x-car[1]/1.3,car[1]/1.1,car[1],car[1])
    } else if (car[0] <= 2){
        if (car[1] > 650 && playerx == 1){
            if (invcible == 0){
                lives--;
                invcible = 10;
            }

        }
        x = 700;
        ctx.drawImage(CAR, x-car[1]/2,car[1],car[1],car[1])
    } else{
        if (car[1] > 600 && playerx == 2){
            if (invcible == 0){
                lives--;
                invcible = 10;
            }

        }
        x = (car[1] + 1755) * 334/800;
        ctx.drawImage(CAR, x-car[1]/4,car[1]/1.1,car[1],car[1])
    }
}
function player(){
    if (playerx == 0){
        ctx.drawImage(MOTORCYCLE, 350-playerOffset/2, 800-playerOffset/2, playerOffset, playerOffset)
    }else if (playerx == 1){
        ctx.drawImage(MOTORCYCLE, 701-playerOffset/2, 800-playerOffset/2, playerOffset, playerOffset);
    }else{
        ctx.drawImage(MOTORCYCLE, 1051-playerOffset/2, 800-playerOffset/2, playerOffset, playerOffset);
    }
}
function incrimentScore(){
    score++;
    ctx.fillStyle = "#33FFF0";
    ctx.font="50px Arial";
    ctx.fillText("Score: " + score/10, 50, 70)
}
function addPeople(){
    if (curr_count == 0) {
        if (Math.random()*100 > 50){
            Car.push([Math.random()*3, 1]);
            curr_count = COUNTDOWN;
        }
    }else{
        if (Math.random()*100 > 95) {
            People.push([0, Math.round(Math.random() * 2)]);
            curr_count = COUNTDOWN;
        }
        curr_count--;
    }
}
function startScreen(){
    ctx.drawImage(BACKGROUND, 0, 0, 1400, 800)
    let line1 = "The Night";
    let line2 = "A 2.5D Runner Game";
    ctx.fillStyle = "#FF5733";
    ctx.font="100px Arial";
    ctx.fillText(line1, 500, 300)
    ctx.font="65px Arial";
    ctx.fillText(line2, 370, 500)
}
function drawlives(){
    for (let i = 1; i <= 3; i++){
        if (lives < i){
            ctx.drawImage(EMPTY, 750 + i*150,0,150,150);
        }else{
            ctx.drawImage(FULL, 750 + i*150,0,150,150);
        }
    }
}
function youDead(){
    ctx.fillStyle = "#FF5733"
    ctx.font="100px Arial";
    ctx.fillText("You lose!", 500, 300)
    ctx.font="75px Arial";
    ctx.fillText("Your score was: " + score, 375, 375)
    setTimeout(fullReset, 5000)
}
function draw() {
    ctx.clearRect(0, 0, mycanvas.width, mycanvas.height);
    if(start == 1){
        ctx.drawImage(OTHERBACKGROUND, 0, 0)
        buildings();
        addPeople();
        for (let i = 0; i < People.length; i++){
            People[i][0] += (People[i][0]/20)+5;
            updatePeople(People[i]);
            if (People[i][0] >= 800){
                People.splice(People[i],1);
                i--;
            }
        }

        player();
        fauna();
        for (let i = 0; i < Car.length; i++){
            Car[i][1] += (Car[i][1]/30)+2;
            updateCar(Car[i]);

            if (i >= Car.length){
                break;
            }
            if (Car[i][1] >= 800){
                Car.splice(Car[i],1);
                i--;
            }
        }
        document.onkeydown = function(evt) {
            evt = evt || window.event;
            if (evt.keyCode == 37) {
                if (playerx != 0) playerx--;
            }
            if (evt.keyCode == 39){
                if (playerx != 2) playerx++;
            }
        };
        incrimentScore();
        drawlives()
        ctx.closePath();
        requestAnimationFrame(draw);
        if (lives == 0){
            gameEnd();
        }
        if (invcible > 0){
            invcible--;
        }
    }
    else if(start == 0){
        startScreen();
        requestAnimationFrame(draw);
    }
    else if(start == 2){
        youDead();
    }
}
draw();
