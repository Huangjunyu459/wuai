package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjy.wuai.mapper.GameMapper;
import com.hjy.wuai.pojo.Category;
import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.pojo.Music;
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
     * 管理员上传游戏
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(Game entity) {
        Game game = new Game();
        game.setGameName(entity.getGameName());
        game.setDescription(entity.getDescription());
        game.setBdSrc(entity.getBdSrc());
        game.setBdCode(entity.getBdCode());
        game.setAuthorId(entity.getAuthorId());
        game.setDCode(entity.getDCode());
        game.setGameCover(entity.getGameCover());
        game.setCategoryId(2);
        game.setIsVip(entity.getIsVip());
        return gameMapper.insert(game) == 1;
    }


    /**
     * 根据 id 获取游戏
     *
     * @param id
     * @return
     */
    @Override
    public Game getById(Serializable id) {
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).eq("id", id);
        return gameMapper.selectOne(wrapper);
    }

    /**
     * 根据 id 获取游戏（未过审）
     *
     * @param id
     * @return
     */
    public Game getByIdNoExamine(Serializable id) {
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0).eq("id", id);
        return gameMapper.selectOne(wrapper);
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
        game.setDescription(entity.getDescription());
        game.setBdSrc(entity.getBdSrc());
        game.setBdCode(entity.getBdCode());
        game.setIsVip(entity.getIsVip());
        return gameMapper.updateById(game) == 1;
    }

    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        return gameMapper.deleteById(id) == 1;
    }


    /**
     * 查询所有已审核的游戏
     *
     * @return
     */
    @Override
    public List<Game> findAllGameExamine() {
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1);
        return gameMapper.selectList(wrapper);
    }

    /**
     * 查询所有未审核的游戏
     *
     * @return
     */
    @Override
    public List<Game> findAllGameNoExamine() {
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0);
        return gameMapper.selectList(wrapper);
    }

    /**
     * 返回最新的八部游戏
     *
     * @return 返回的结果
     */
    @Override
    public List<Game> findEightGame() {
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").eq("examine", 1).last("limit 8");
        return gameMapper.selectList(wrapper);
    }

    /**
     * 返回最新的十六部游戏
     *
     * @return 返回的结果
     */
    @Override
    public List<Game> findSixthGame() {
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id").eq("examine", 1).last("limit 16");
        return gameMapper.selectList(wrapper);
    }

    /**
     * 审核功能
     *
     * @param id 游戏 id
     * @return 返回的结果
     */
    @Override
    public boolean examine(String id) {
        Game game = gameMapper.selectById(id);
        game.setExamine(1);
        return gameMapper.updateById(game) == 1;
    }

    /**
     * 根据 作品名 模糊查询（已过审）
     *
     * @param gameName 游戏名
     * @return 返回的结果
     */
    @Override
    public List<Game> findGameByGameNameExamine(String gameName) {
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.like("game_name", gameName).eq("examine", 1);
        return gameMapper.selectList(wrapper);
    }

    /**
     * 根据 作品名 模糊查询（未过审）
     *
     * @param gameName 游戏名
     * @return 返回的结果
     */
    @Override
    public List<Game> findGameByGameNameNoExamine(String gameName) {
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.like("game_name", gameName).eq("examine", 0);
        return gameMapper.selectList(wrapper);
    }

    /**
     * 根据游戏名名 模糊查询（已删除的游戏）
     *
     * @param gameName 游戏名
     * @return 返回的结果
     */
    @Override
    public List<Game> findGameByGameNameIsDelete(String gameName) {
        gameName = "%" + gameName + "%";
        return gameMapper.findGameByGameNameIsDelete(gameName);
    }

    /**
     * 点赞功能
     *
     * @param id
     * @return
     */
    @Override
    public boolean likes(String id) {
        Game game = gameMapper.selectById(id);
        game.setLove(game.getLove() + 1);
        return gameMapper.updateById(game) == 1;
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
     * 分页查询（过审）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Game> pagingQueryExamine(String gameName, Integer index, Integer size) {
        IPage<Game> page = new Page<>(index, size);
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 1).like("game_name", gameName);
        IPage<Game> gameIPage = gameMapper.selectPage(page, wrapper);
        return gameIPage;
    }

    /**
     * 分页查询（未过审）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Game> pagingQueryNoExamine(String gameName, Integer index, Integer size) {
        IPage<Game> page = new Page<>(index, size);
        QueryWrapper<Game> wrapper = new QueryWrapper<>();
        wrapper.eq("examine", 0).like("game_name", gameName);
        IPage<Game> gameIPage = gameMapper.selectPage(page, wrapper);
        return gameIPage;
    }

    /**
     * 分页查询（已删除的分类）
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public List<Game> pagingQueryIsDelete(String gameName, Integer index, Integer size) {
        gameName = "%" + gameName + "%";
        List<Game> gameList = gameMapper.pagingQueryIsDelete(gameName, index, size);
        return gameList;
    }

    /**
     * 根据 游戏的 id 查询 所属的分类名
     *
     * @param gid 游戏 id
     * @return 分类的名称
     */
    @Override
    public String findCategoryNameByGid(String gid) {
        return gameMapper.findCategoryNameByGid(gid);
    }
}
