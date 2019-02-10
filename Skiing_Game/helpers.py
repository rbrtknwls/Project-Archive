# -------- IMPORTS -------- #

import pygame

# ------- CONSTANTS ------- #

BLACK = (0,0,0)
WHITE = (255,255,255)
BLUE = (0,127,255)

SCREEN_HEIGHT = 900
SCREEN_WIDTH = 1200


RIGHT = pygame.image.load("Right.png")
LEFT = pygame.image.load("Left.png")
UP = pygame.image.load("Up.png")
TREE = pygame.image.load("Tree.png")
ROCK = pygame.image.load("Rock.png")

# -------- CLASSES -------- #

class Skier(pygame.sprite.Sprite):
        def __init__(self):
                super(Skier,self).__init__()
                self.image = UP
                self.rect = self.image.get_rect()
                self.curr = 1

class Tree(pygame.sprite.Sprite):
        def __init__(self):
                super(Tree,self).__init__()
                self.image = TREE
                self.rect = self.image.get_rect()
        def reset_pos(self):
                self.image = TREE
                self.rect = self.image.get_rect()
                self.rect.y = 0
                self.rect.x = -200

        def update_R(self):
                self.image = TREE
                w,h = self.image.get_size()
                scale = self.rect.centery*1.5
                self.image = pygame.transform.scale(self.image, (int(w+scale), int(h+scale)))

                self.rect.y += 2.5 + scale/20
                self.rect.x = (900 + self.rect.y)/1.5
                if self.rect.y > 450:
                    self.reset_pos()
        def update_L(self):
                self.image = TREE
                w,h = self.image.get_size()
                scale = self.rect.centery*1.5
                self.image = pygame.transform.scale(self.image, (int(w+scale), int(h+scale)))

                self.rect.y += 2.5 + scale/20
                self.rect.x = ((-900 + self.rect.y)/1.5)*-1 - (w+scale)
                
                if self.rect.y > 450:
                    self.reset_pos()
class Rock(pygame.sprite.Sprite):
        def __init__(self):
                super(Rock,self).__init__()
                self.image = ROCK
                self.rect = self.image.get_rect()
        def create(self):
                self.image = ROCK
                self.rect = self.image.get_rect()
                self.rect.y = 30
                self.rect.centerx = -200

        def update_L(self):
                self.image = ROCK
                w,h = self.image.get_size()
                scale = self.rect.centery/9
                self.image = pygame.transform.scale(self.image, (int(w+scale), int(h+scale)))

                self.rect.y += (2.5 + scale/15)*5
                self.rect.left = (2298 - self.rect.y)/3.83 -(scale)
        def update_R(self):
                self.image = ROCK
                w,h = self.image.get_size()
                scale = self.rect.centery/9
                self.image = pygame.transform.scale(self.image, (int(w+scale), int(h+scale)))
                self.rect.y += (2.5 + scale/15)*5
                self.rect.right = (-2298 - self.rect.y)/3.83*-1 
        def update_M(self):
                self.image = ROCK
                w,h = self.image.get_size()
                scale = self.rect.centery/9
                self.image = pygame.transform.scale(self.image, (int(w+scale), int(h+scale)))

                self.rect.y += (2.5 + scale/15)*5
                self.rect.centerx = 600 - (scale)/2
        
                
        
                


