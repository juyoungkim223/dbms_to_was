package com.common.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class demoController {
    List<String> perm = new ArrayList<>();
    List<char[]> comb = new ArrayList<>();
    List<String> perm2 = new ArrayList<>();
    @GetMapping("/htmlhome")
    public String mainPage(Model model) {
        setPerm(new char[]{'a', 'b' , 'c', 'd'},0, 2);
        model.addAttribute("perm2", perm2);
        model.addAttribute("message", "thyme");
        return "htmlhome";
    }
    public void comb(char[] arr, int depth, int r, boolean[] visited) {
        if(depth == r) {
            String s = "";
            for(int i = 0; i < visited.length; i++) {
                if(visited[i]) s += arr[i];
            }
            System.out.println(s);
            if(s.length() == 3)
            comb.add(s.toCharArray());
            return;
        }

        for(int i = depth; i < arr.length; i++){
            visited[i] = true;
            comb(arr, depth + 1, r, visited);
            visited[i] = false;
        }
    }
    public void setPerm(char[] arr, int depth, int r) {
        comb(arr, 0,3, new boolean[arr.length]);
        for(char[] combArr : comb) {
            System.out.println(Arrays.toString(combArr));
            perm(combArr, depth, r);
        }
    }
    public void perm(char[] arr, int depth, int r){
        if(r == depth) {
            String res = "";
            for(char c : arr) {
                res += c + " ";
            }
            perm2.add(res);
        }

        for(int i = depth; i < arr.length; i++) {
            char tmp = arr[depth];
            arr[depth] = arr[i];
            arr[i] = tmp;
            perm(arr, depth + 1, r);
            // roll back
            char tmpRollBack = arr[depth];
            arr[depth] = tmp;
            arr[i] = tmpRollBack;
        }
    }
}
