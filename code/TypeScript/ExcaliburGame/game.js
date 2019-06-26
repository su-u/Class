// game.js

// Create an instance of the engine.
var game = new ex.Engine({
    width: 800,
    height: 600,
  })
  
  // Create an actor with x position of 150px,
  // y position of 40px from the bottom of the screen,
  // width of 200px, height and a height of 20px
  var paddle = new ex.Actor(150, game.drawHeight - 40, 200, 20)
  
  // Let's give it some color with one of the predefined
  // color constants
  paddle.color = ex.Color.Chartreuse
  
  // Make sure the paddle can partipate in collisions, by default excalibur actors do not collide
  paddle.collisionType = ex.CollisionType.Fixed
  
  // `game.add` is the same as calling
  // `game.currentScene.add`
  game.add(paddle)
  
  // Start the engine to begin the game.
  game.start()