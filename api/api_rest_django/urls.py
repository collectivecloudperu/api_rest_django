"""api_rest_django URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import include, path

from rest_framework.routers import DefaultRouter
 
# Importamos Django REST Framework, también la vista 'jugos' y postres
from jugos import views as jugos_views
from postres import views as postres_views 

# Simple JWT 
from rest_framework_simplejwt import views as jwt_views 

# Configuración para cargar las imágenes de la API REST 
from django.urls import re_path as url
from django.conf import settings
from django.views.static import serve
 
router = DefaultRouter()
router.register(r'jugos', jugos_views.JugosViewSet, basename='jugos')
router.register(r'postres', postres_views.PostresViewSet, basename='postres')
 
urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include(router.urls)),

    # Rutas para generar y refrescar los JWT (JSON Web Tokens)
    path('api/token/', jwt_views.TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('api/token/refresh/', jwt_views.TokenRefreshView.as_view(), name='token_refresh'), 

    # Ruta para cargar las imágenes de la API REST 
    url(r'^jugos/(?P<path>.*)$', serve, {'document_root': settings.MEDIA_ROOT, })
]
