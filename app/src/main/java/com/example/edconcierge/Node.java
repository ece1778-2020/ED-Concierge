package com.example.edconcierge;

import static java.lang.Math.abs;

public class Node {
    private int G;
    private int H;
    private int F;
    private Node parent;
    private boolean block;
    private int row;
    private int column;

    public Node(int row,int column){
        this.row=row;
        this.column=column;
    }

    @Override
    public boolean equals(Object obj) {
        Node other = (Node) obj;
        return this.getRow() == other.getRow() && this.getColumn() == other.getColumn();
    }

    public int calculateH(Node endNode){
        //Manhattan distance
        int h = abs(endNode.getRow() - getRow()) + abs(endNode.getColumn() - getColumn());
        return h;
    }
    public int calculateG(Node startNode){
        int g = abs(startNode.getRow() - getRow()) + abs(startNode.getColumn() - getColumn());
        return g;
    }

    @Override
    public String toString() {
        return "Node{" +
                "G=" + G +
                ", H=" + H +
                ", F=" + F +
                ", row=" + row +
                ", column=" + column +
                '}';
    }

    //setter//
    public void setH(int h) {
        H = h;
    }

    public void setG(int g) {
        G = g;
    }

    public void setF(int f) {
        F = f;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setBlock(boolean block) {
        this.block = block;
    }

    //getter
    public boolean isBlock() {
        return block;
    }

    public int getColumn() {
        return column;
    }

    public int getF() {
        return F;
    }

    public int getG() {
        return G;
    }

    public int getRow() {
        return row;
    }

    public Node getParent() {
        return parent;
    }
    public int getH() {
        return H;
    }

}

