from rest_framework import serializers
from postres.models import Postres 

class PostresSerializer(serializers.ModelSerializer):
    class Meta:
        model = Postres
        fields = ['id', 'nombre', 'precio', 'stock', 'img', 'created_at', 'updated_at']
        extra_kwargs = {'id': {'required': False}}

