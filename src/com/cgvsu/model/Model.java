package com.cgvsu.model;
import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;

import java.util.*;

public class Model implements com.cgvsu.Model {
    public List<Vector3f> vertices = new ArrayList<>();
    public List<Vector2f> textureVertices = new ArrayList<>();
    public List<Vector3f> normals = new ArrayList<>();
    public List<Polygon> polygons = new ArrayList<>();

    // Геттеры
    @Override
    public List<Vector3f> getVertices() {
        return vertices;
    }

    @Override
    public List<Vector2f> getTextureVertices() {
        return textureVertices;
    }

    @Override
    public List<Vector3f> getNormals() {
        return normals;
    }

    @Override
    public List<Polygon> getPolygons() {
        return polygons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model model)) return false;
        return Objects.equals(vertices, model.vertices) && Objects.equals(textureVertices, model.textureVertices) && Objects.equals(normals, model.normals) && Objects.equals(polygons, model.polygons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices, textureVertices, normals, polygons);
    }
}