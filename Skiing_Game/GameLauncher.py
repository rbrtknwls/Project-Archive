#!/usr/bin/python
import pygame
import os
import random
import codecs
from helpers import *
#from gi.repository import Gtk
Done = False

class TextMatchGame:

        def __init__(self):
                # Varibles
                self.score = 0
                self.game_over = False
         
                # Create sprite lists
                #   Player
                self.skier_l = []

                #   Trees
                self.tree_list_R = []
                self.tree_list_L = []

                #   Rock
                self.rock_list_R = []
                self.rock_list_M = []
                self.rock_list_L = []

                #   All Sprites
                self.all_sprites_list = pygame.sprite.Group()
         
                # Create the tree sprites
                for i in range(1):
                    tree_R = Tree()
                    tree_L = Tree()
                   
                    tree_L.rect.y = i*i
                    tree_R.rect.y = i*i
                    
                    w,h = tree_R.image.get_size()
                    scale = tree_R.rect.centery
                    tree_R.image = pygame.transform.scale(tree_R.image, (int(w+scale), int(h+scale)))

                    w,h = tree_L.image.get_size()
                    scale = tree_L.rect.centery
                    tree_L.image = pygame.transform.scale(tree_L.image, (int(w+scale), int(h+scale)))

                    tree_L.rect.y = i*i*2.5
                    tree_L.rect.x = ((-900 + tree_L.rect.y)/1.5)*-1 - (w+scale)
         
                    tree_R.rect.y = i*i*2.5
                    tree_R.rect.x = (900 + tree_R.rect.y)/1.5

         
                    self.tree_list_R.append(tree_R)
                    self.tree_list_L.append(tree_L)
                    
                    self.all_sprites_list.add(tree_R)
                    self.all_sprites_list.add(tree_L)
                    
                # Player Creation
                skier = Skier()
                skier.rect.centerx = 600
                skier.rect.y = 500
                self.skier_l.append(skier)

                # Debug
        

 

                                
        

	def process_events(self):
                # Import Player
                skier = self.skier_l[0]
                for event in pygame.event.get():
                    if event.type == pygame.QUIT:
                        return True
                    if event.type == pygame.MOUSEBUTTONDOWN:
                        if self.game_over:
                            self.__init__()
                    # UI
                    if event.type == pygame.KEYDOWN:
                        if event.key == pygame.K_LEFT and skier.rect.centerx > 435:
                                skier.rect.centerx -= 235
                                skier.image = LEFT
            
                        if event.key == pygame.K_RIGHT and skier.rect.centerx < 765:
                                skier.rect.centerx += 235
                                skier.image = RIGHT
                    else:
                        skier.image = UP

                        
                    
         
                return False

	# This function reads in words.csv, splits each row into a list and adds each list to a wordlist
        def display_frame(self, screen):
                screen.fill(BLACK)
                if not self.game_over:
                    self.score += 0.2
                    # font = pygame.font.Font("Serif", 25)
                    font = pygame.font.SysFont("serif", 25)
                    text = font.render("Score: " + str(int(self.score)), True, BLACK)
                    center_x = (text.get_width() // 2)
                    center_y = (text.get_height() // 2)
                    screen.blit(text, [center_x, center_y])
         
                if not self.game_over:
                        pygame.draw.line(screen, WHITE,(610,40),(1070, 850))
                        pygame.draw.line(screen, WHITE,(590,40),(130, 850))
                        pygame.draw.line(screen, WHITE,(600,0),(600,0))
                        screen.blit(self.skier_l[0].image,[self.skier_l[0].rect.x,self.skier_l[0].rect.y])
                        self.all_sprites_list.draw(screen)
                       
         
                pygame.display.flip()

        
        def update(self):
                
		global Done
                
                if not self.game_over:
                    # Update
                    #   Tree
                    for i in range (1):
                            self.tree_list_L[i].update_L()
                            self.tree_list_R[i].update_R()
                    #   Rock
                    
                    if random.randrange(0,100-int(self.score/20)) == 1:
                        rock = Rock()
                        rock.create()
                        self.rock_list_L.append(rock)
                        self.all_sprites_list.add(rock)
                    elif random.randrange(0,100-int(self.score/20)) == 1:
                        rock = Rock()
                        rock.create()
                        self.rock_list_M.append(rock)
                        self.all_sprites_list.add(rock)
                    elif random.randrange(0,100-int(self.score/20)) == 1:
                        rock = Rock()
                        rock.create()
                        self.rock_list_R.append(rock)
                        self.all_sprites_list.add(rock)
                        
                    for i in range(len(self.rock_list_R)):
                        if not (i >= len(self.rock_list_R)):
                            self.rock_list_R[i].update_R()
                            if self.rock_list_R[i].rect.y > 650 and self.skier_l[0].rect.centerx == 835:
                                Done = True
                            if self.rock_list_R[i].rect.bottom > 900:
                                self.rock_list_R.pop(i)
                                
                    for i in range(len(self.rock_list_M)):
                        if not (i >= len(self.rock_list_M)):
                            self.rock_list_M[i].update_M()
                            if self.rock_list_M[i].rect.y > 650 and self.skier_l[0].rect.centerx == 600:
                                Done = True
                            if self.rock_list_M[i].rect.bottom > 900:
                                self.rock_list_M.pop(i)
                    for i in range(len(self.rock_list_L)):
                        if not (i >= len(self.rock_list_L)):                      
                            self.rock_list_L[i].update_L()
                            if self.rock_list_L[i].rect.y > 650 and self.skier_l[0].rect.centerx == 365:
                                Done = True
                            if self.rock_list_L[i].rect.bottom > 900:
                                self.rock_list_L.pop(i)
        def run(self):
                global Done

                score = 0
                screen = pygame.display.get_surface()
                

                while not Done:
                
                        #while Gtk.events_pending():
 #                               Gtk.main_iteration()

        # Process events (keystrokes, mouse clicks, etc)
                        done = self.process_events()
 
        # Update object positions, check for collisions
                        self.update()
 
        # Draw the current frame
                        self.display_frame(screen)

                  
                

        

# if run directly - use the main method

def main():
    # Initialize Pygame and set up the window
    pygame.init()
 
    size = [SCREEN_WIDTH, SCREEN_HEIGHT]
    screen = pygame.display.set_mode(size)
 
    pygame.display.set_caption("Sking Game")
    pygame.mouse.set_visible(False)

    game = TextMatchGame()
    game.run()
 

        




    


        
        


 
    # Close window and exit
    pygame.quit()

if __name__ == '__main__':
	main()
