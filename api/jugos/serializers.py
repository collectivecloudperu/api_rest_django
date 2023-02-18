from rest_framework import serializers
from jugos.models import Jugos

class JugosSerializer(serializers.ModelSerializer):
    class Meta:
        model = Jugos
        fields = ['id', 'nombre', 'precio', 'stock', 'img', 'created_at', 'updated_at']
        extra_kwargs = {'id': {'required': False}}