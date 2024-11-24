package com.cgvsu;

import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Polygon;

import java.util.List;

public interface Model {
    List<Vector3f> getVertices();
    List<Vector2f> getTextureVertices();
    List<Vector3f> getNormals();
    List<Polygon> getPolygons();
}
