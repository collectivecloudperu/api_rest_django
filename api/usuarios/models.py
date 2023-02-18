from django.db import models
from django.contrib.auth.models import AbstractUser
 
# Campos de la tabla 'auth_user' 
class Usuarios(AbstractUser):
    username = models.CharField(max_length=150, default='DEFAULT VALUE')
    password = models.CharField(max_length=128, default='DEFAULT VALUE')
    email = models.CharField(max_length=254, default='DEFAULT VALUE', unique=True)
    date_joined = models.DateTimeField(auto_now_add=True)
 
    class Meta:
         db_table = 'auth_user' # El nombre de la tabla de usuarios 

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []

