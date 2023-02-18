from django.shortcuts import render

# Elementos necesarios para que el API REST funcione 
from rest_framework import viewsets
from django.http import Http404
from rest_framework import status
from rest_framework.response import Response

# Clase 'PostresSerializer' 
from postres.serializers import PostresSerializer

# Modelo 'Postres' 
from postres.models import Postres

# Importo la clase 'IsAuthenticated' de Django Rest Framework 
from rest_framework.permissions import IsAuthenticated

class PostresViewSet(viewsets.ModelViewSet):
    
    permission_classes = [IsAuthenticated] # Llamo a la clase 'IsAuthenticated' de Django Rest Framework 
    queryset = Postres.objects.all().order_by('id')
    serializer_class = PostresSerializer
     