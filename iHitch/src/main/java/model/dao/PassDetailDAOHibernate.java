package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.PassDetail;
import model.dao.superInterface.PassDetailDAO;

@Repository
public class PassDetailDAOHibernate implements PassDetailDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public PassDetail select(int id) {
		return this.getSession().get(PassDetail.class, id);
	}

	@Override
	public List<PassDetail> select() {
		return this.getSession().createQuery("FROM PassDetail", PassDetail.class).list();
	}

	@Override
	public Integer insert(PassDetail bean) {
		if (bean != null) {
			return (Integer) this.getSession().save(bean);
		}
		return null;
	}

	@Override
	public PassDetail update(PassDetail bean) {
		if (bean != null) {
			PassDetail result = this.getSession().get(PassDetail.class, bean.getDetailId());
			if (result != null) {
				result.setMid(bean.getMid());
				result.setRideId(bean.getRideId());
				result.setSeatsBooked(bean.getSeatsBooked());
				result.setBookTime(bean.getBookTime());
				result.setConfirmed(bean.getConfirmed());
			}
			return result;
		}
		return null;
	}
	
	@Override
	public boolean delete(int id) {
		PassDetail temp = this.getSession().get(PassDetail.class, id);
		if(temp!=null) {
			this.getSession().delete(temp);
			return true;
		}
		return false;
	}
}
