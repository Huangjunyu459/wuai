package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.GameMapper;
import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-03-01
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {

    @Autowired
    private GameMapper gameMapper;

    /**
     * 上传
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(Game entity) {
        return gameMapper.insert(entity) == 1 ? true : false;
    }


    /**
     * 根据 id 获取游戏
     *
     * @param id
     * @return
     */
    @Override
    public Game getById(Serializable id) {
        return gameMapper.selectById(id);
    }

    /**
     * 游戏更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 游戏实体
     * @return
     */
    @Override
    public boolean updateById(Game entity) {

        //  获取要更新的用户
        Game game = getById(entity.getId());
        game.setGameName(entity.getGameName());
        game.setBdSrc(entity.getBdSrc());
        game.setBdCode(entity.getBdCode());
        return gameMapper.updateById(game) == 1 ? true : false;
    }

    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        return gameMapper.deleteById(id) == 1 ? true : false;
    }


    /**
     * 查询所有游戏
     *
     * @return
     */
    @Override
    public List<Game> findAllGame() {
        return gameMapper.selectList(null);
    }

    /**
     * 根据 作品名 模糊查询
     *
     * @param gameName
     * @return
     */
    @Override
    public List<Game> findGameByGameName(String gameName) {
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.like("game_name", gameName);
        return gameMapper.selectList(wrapper);
    }


    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    @Override
    public boolean likes(Long id) {
        Game game = gameMapper.selectById(id);
        game.setLove(game.getLove() + 1);
        return gameMapper.updateById(game) == 1 ? true : false;
    }

    /**
     * 查询已删除的游戏
     *
     * @return
     */
    @Override
    public List<Game> findIsDelete() {
        return gameMapper.findIsDelete();
    }

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    @Override
    public IPage<Game> pagingQuery(Integer index) {
        IPage<Game> page = new Page<>(index, 5);
        IPage<Game> gameIPage = gameMapper.selectPage(page, null);
        return gameIPage;
    }

    /**
     * 根据 游戏的 id 查询 所属的分类名
     *
     * @param gid 游戏 id
     * @return 分类的名称
     */
    @Override
    public String findCategoryNameByGid(Long gid) {
        return gameMapper.findCategoryNameByGid(gid);
    }
}
