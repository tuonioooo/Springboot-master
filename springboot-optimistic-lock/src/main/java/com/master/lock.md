# 数据乐观锁并发实现方案

- 通过版本号实现

  ```
  update t_goods_info
  set
  	amout = amout-#{buys}, version=version+1
  where
      code = #{code} and version=#{version}
  ```

- 通过状态控制

```
update t_goods_info
set
	amout = amout-#{buys}
where
    code = #{code} and amout-#{buys} >=0
```

# 代码示例



