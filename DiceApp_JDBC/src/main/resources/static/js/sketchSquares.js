let sketch = function(p) {


  p.setup = function() {
    p.background(0);
    p.createCanvas(700, 410);
  };

  p.draw = function() {
    let r = 100;  
    let g = p.map(p.mouseX,0,p.width,0,255,true);
    let b = p.map(p.mouseY,0,p.height,0,255,true);
    //p.background(0,10);
    p.fill(r,g,b);
    p.rect(p.mouseX, p.mouseY, 50, 50);
  };

  p.keyTyped = function(){
    if (p.key === 'a') {
      p.background(0);
    }
  };
};

let sketch2 = function(p) {
  let x = 100;
  let y = 100;

  p.setup = function() {
    p.createCanvas(700, 410);
  };

  p.draw = function() {
    p.background(0);
    p.fill(255,120,50);
    p.rect(p.mouseX, p.mouseY, 50, 50);
  };
  p.keyTyped = function(){
    if (p.key === 'a') {
      p.background(0);
    }
  };
};

mySketch = new p5(sketch, 'container');
mySketch2 = new p5(sketch2, 'container2');