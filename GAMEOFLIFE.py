from SimpleGraphics import *
from time import sleep
from random import randint

setAutoUpdate(False)

background("black")
setOutline("blue")

NUMBER = 50

class Box:
	
	def __init__(self):
		self.x = 800 / NUMBER
		self.y = 600 / NUMBER
		self.w = 800 / NUMBER
		self.h = 600 / NUMBER
		self.color = randint(0, 1)
		
	def position(self, x, y):
		self.x = x * 800 / NUMBER
		self.y = y * 600 / NUMBER
		
	def draw(self):
		if self.color == 1:
			setFill("white")
		else:
			setFill("black")
		rect(self.x, self.y, self.w, self.h)
		
	def change(self):
		if self.color == 1:
			self.color = 0
		else:
			self.color = 1
			
prev = [[0 for x in range(NUMBER)] for y in range(NUMBER)]
drawing = [[Box() for x in range(NUMBER)] for y in range(NUMBER)]
#initialize the drawing
tempx = 0
tempy = 0
for col in drawing:
	tempx = 0
	for item in col:
		item.position(tempx, tempy)
		tempx += 1
	tempy += 1

for col in drawing:
	for item in col:
		item.draw()
#the loop
while not closed():	
	#copy to prev
	for i in range(0, NUMBER):
		for j in range(0, NUMBER):
			prev[i][j] = drawing[i][j].color
			
	#change the color
	for i in range(0, NUMBER):
		for j in range(0, NUMBER):
			count = 0
			
			count += prev[(i-1+NUMBER)%NUMBER][(j-1+NUMBER)%NUMBER]
			count += prev[(i-1+NUMBER)%NUMBER][j]
			count += prev[(i-1+NUMBER)%NUMBER][(j+1)%NUMBER]
			count += prev[i][(j-1+NUMBER)%NUMBER]
			count += prev[i][(j+1)%NUMBER]
			count += prev[(i+1)%NUMBER][(j-1+NUMBER)%NUMBER]
			count += prev[(i+1)%NUMBER][j]
			count += prev[(i+1)%NUMBER][(j+1)%NUMBER]
			
			if prev[i][j] == 1:
				if count < 2:
					drawing[i][j].change()
					drawing[i][j].draw()
				elif count > 3:
					drawing[i][j].change()
					drawing[i][j].draw()
			else:
				if count == 3:
					drawing[i][j].change()
					drawing[i][j].draw()
			
	#drawing
	#for col in drawing:	
		#for item in col:
			#item.draw()
