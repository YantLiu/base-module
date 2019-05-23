package com.base.common.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ace on 2017/6/12.
 */
@Data
public class TreeNode {
    protected Long id;
    protected Long parentId;
    List<TreeNode> children = new ArrayList<>();

    public void add(TreeNode node){
        children.add(node);
    }
}
