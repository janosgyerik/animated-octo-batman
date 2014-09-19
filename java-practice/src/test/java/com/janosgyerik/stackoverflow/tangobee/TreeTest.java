package com.janosgyerik.stackoverflow.tangobee;

import org.junit.Test;

import java.util.*;

class Node<T> {
    final T data;

    final Set<Node<T>> children = new HashSet<>();

    Node(T data) {
        this.data = data;
    }

    public Set<Node<T>> getChildren() {
        return children;
    }

    public Node<T> addChild(Node<T> child) {
        children.add(child);
        return child;
    }

    public static <T> void recursive(Node<T> node) {
        System.out.println(node.data);

        for (Node<T> child : node.getChildren()) {
            recursive(child);
        }
    }
}

public class TreeTest {
    Node<String> createNode(String data) {
        return new Node<>(data);
    }

    @Test
    public void test() {
        Node<String> root = createNode("Multiverse");
        Node<String> galaxy = root.addChild(createNode("Universe"))
                .addChild(createNode("Supercluster")
                        .addChild(createNode("Galaxy")));
        galaxy.addChild(createNode("Andromeda"));
        galaxy.addChild(createNode("Milky way"));
        root.addChild(createNode("Underverse")).addChild(createNode("Hades"));
        Node.recursive(root);
    }

}
