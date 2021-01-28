package cn.jboost.springboot.security.username;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * UserDetails 校验器组合
 * @Author ronwxy
 * @Date 2020/8/8 10:23
 * @Version 1.0
 */
public class CompositeUserDetailsChecker implements UserDetailsChecker {
    private List<UserDetailsChecker> checkers;

    public CompositeUserDetailsChecker(List<UserDetailsChecker> checkers) {
        this.checkers = checkers;
    }

    public List<UserDetailsChecker> getCheckers() {
        return Optional.ofNullable(checkers).orElse(new ArrayList<>());
    }

    @Override
    public void check(UserDetails toCheck) {
        if (CollectionUtils.isEmpty(checkers)) {
            return;
        }
        checkers.stream().forEach(c -> c.check(toCheck));
    }
}
