package com.example.roleBased.config;

import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FeatureExtractor {

    public double jaccardSimilarity(Set<String> set1,Set<String> set2){
        Set<String> interaction = set1.stream().filter(set2::contains).collect(Collectors.toSet());
        Set<String> union = set1.stream().collect(Collectors.toSet());
        union.addAll(set2);
        return (double) interaction.size() / union.size();
    }

    public FeatureExtractor(Set<String> set1,Set<String> set2) {
    }
}
