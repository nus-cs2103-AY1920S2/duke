Git Notes taken from: https://www.atlassian.com/git/tutorials/inspecting-a-repository/git-tag
# Pushing to Git with tag
`git tag [tag]` to tag
`git push origin [tag]' to push to origin with tag

# Tagging Old Commits
`git tag [tag] [commit-ref]' to tag a old commit
can then push as above

# Updating Branch from Master
git stash          #  move local uncomitted changes away
git checkout feature-branch
git rebase --onto v2.0 origin/master
git stash apply    # reapply uncommitted changes

# Checkout to new, working branch and merging changes
In fact, if you make a branch as soon as you start work, you can "commit" to your branch, have a trail and backup of your work, without having to have a stable codeset. Once you are happy with your work, you can merge it back into your "master" branch.
```
    To branch your repo: git checkout -b MyNewBranch
    To push committed changes of your new branch: git push origin MyNewBranch
    To check out a branch on another machine: git checkout MyNewBranch
    To switch to another branch (e.g. "master"): git checkout master
    When in master, to merge MyNewBranch back in: git merge MyNewBranch
    To list branches: git branch
```

# Exporting Jar
`./gradlew shadowjar` in CLT, version numbers managed in build.gradle, output files in /build/libs