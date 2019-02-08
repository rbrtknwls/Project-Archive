/**
 -------- CONSTANTS --------
 */

var ALIEN_1  = document.createElement("img");
ALIEN_1.src = "images/plalien.jpg";
var SHIP  = document.createElement("img");
SHIP.src = "images/plship.jpg";
var BACKGROUND  = document.createElement("img");
BACKGROUND.src = "images/back.png";


var mycanvas = document.getElementById('canvas');
var ctx = mycanvas.getContext('2d');

var bricks = [];
var shots = [];
var brickRowCount = 16;
var brickColumnCount = 5;
var brickWidth = 60;
var brickHeight = 60;
var brickPadding = 10;
var brickOffsetTop = 30;
var brickOffsetLeft = 50;
let score = 0;
var countdown = 50;
var limit = 0;

var lazy = 0;
var PLAYERWIDTH = 120;
var PLAYERHEIGHT = 120;
var PlayerX = 1200/2-PLAYERWIDTH/2;
var PlayerY = 600-PLAYERHEIGHT;
var canmake = true;
let rightPressed = false;
let leftPressed = false;

for(c=0; c<brickColumnCount; c++) {
    bricks[c] = [];
    for(r=0; r<brickRowCount; r++) {
        bricks[c][r] = { x: 0, y: 0, status: 1 };
    }
}

for(c=0; c<brickColumnCount; c++) {
    for (r = 0; r < brickRowCount; r++) {
        if (bricks[c][r].status == 1) {
            var brickX = (r * (brickWidth + brickPadding)) + brickOffsetLeft;
            var brickY = (c * (brickHeight + brickPadding)) + brickOffsetTop;
            bricks[c][r].x = brickX;
            bricks[c][r].y = brickY;
        }
    }
}

document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);

function keyDownHandler(e) {
    if (e.keyCode == 32){
        if (canmake == true){
            makeBullet();
        }
    }
    if(e.keyCode == 39) {
        rightPressed = true;
    }
    else if(e.keyCode == 37) {
        leftPressed = true;
    }
}
function keyUpHandler(e) {
    if (e.keyCode == 32){
        canmake = true;
    }
    if(e.keyCode == 39) {
        rightPressed = false;
    }
    else if(e.keyCode == 37) {
        leftPressed = false;
    }
}


function makeBullet(){
    shots.push({x:PlayerX+PLAYERWIDTH/2-15, y: 600-PLAYERHEIGHT, status: 1});
    canmake = false;
}
function drawBricks() {
    countdown --;
    if (countdown == 0){
        countdown = 2
        for(c=0; c<brickColumnCount; c++) {
            for(r=0; r<brickRowCount; r++) {
                bricks[c][r].x += 15;
                if(bricks[c][r].x + brickWidth > 1200){
                    bricks[c][r].x = 0;
                    bricks[c][r].y += 70;
                }
                if(bricks[c][r].y > 400 && bricks[c][r].status == 1){
                    alert("GAME OVER!");
                    for(c1=0; c1<brickColumnCount; c1++) {
                        for (r1 = 0; r1 < brickRowCount; r1++) {
                            bricks[c1][r1].status = 0;
                        }
                    }
                    document.location.reload();
                }
                if(bricks[c][r].status == 1) {
                    ctx.drawImage(ALIEN_1, bricks[c][r].x, bricks[c][r].y, brickWidth, brickHeight);


                }
            }
        }
    }else{
        for(c=0; c<brickColumnCount; c++) {
            for(r=0; r<brickRowCount; r++) {

                if(bricks[c][r].status == 1) {
                    ctx.drawImage(ALIEN_1, bricks[c][r].x, bricks[c][r].y, brickWidth, brickHeight);

                }
            }
        }
    }

}

function drawShip(){
    if(rightPressed && PlayerX < canvas.width-PLAYERWIDTH) {
        PlayerX += 7;
    }
    else if(leftPressed && PlayerX > 0) {
        PlayerX -= 7;
    }
    ctx.drawImage(SHIP, PlayerX, PlayerY, PLAYERWIDTH, PLAYERHEIGHT);
}

function drawBullet(){
    console.log(shots.length);
    for (let i = lazy; i < shots.length; i++){
        shots[i].y -= 15;
        if (shots[i].y > -30){
            if (shots[i].status == 1) {
                ctx.drawImage(SHIP, shots[i].x, shots[i].y, 30, 30);
            }
        }else{
            lazy++;
        }

    }
}

function collisionDetection() {
    for (let i = lazy; i < shots.length; i++) {
        for (c = 0; c < brickColumnCount; c++) {
            for (r = 0; r < brickRowCount; r++) {
                var bull = shots[i]
                var b = bricks[c][r];
                if (b.status == 1) {
                    if (bull.x > b.x && bull.x < b.x + brickWidth && bull.y > b.y && bull.y < b.y + brickHeight && bull.status == 1) {
                        b.status = 0;
                        bull.status = 0;
                        score++
                        if (score == brickRowCount * brickColumnCount) {
                            alert("YOU WIN, CONGRATS!");
                            document.location.reload();
                        }
                    }
                }
            }
        }
    }
}

function draw(){
    ctx.drawImage(BACKGROUND, 0,0,1200,800)
    drawBricks()
    drawShip()
    drawBullet()
    collisionDetection();
    document.getElementById("score").innerHTML = "Score: " + score*10;
    requestAnimationFrame(draw);
}
draw();

